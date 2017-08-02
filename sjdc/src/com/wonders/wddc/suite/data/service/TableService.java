package com.wonders.wddc.suite.data.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.Service;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.wonders.wddc.suite.csrq.comparator.OrderComparator;
import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.csrq.entity.ColumnConfigBo;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.tiles.jk.DBpool;
import com.wonders.wddc.tiles.jk.SqlExecutor;
@IocBean(fields = "dao")
public class TableService extends Service{
	@Inject
	private SqlExecutor executor;
	@Inject
	private DBpool dbPool;
	
	public void changeTable(final TableConfigBo tableConfigBo, final List<ColumnConfigBo> columnList){
		Trans.exec(new Atom(){
			public void run(){
				//表配置信息
				tableConfigBo.setValidity("1");
				//表配置信息未存储时
				if (tableConfigBo.getThemeId() == null){
				     Criteria cri = Cnd.cri();
				     cri.getOrderBy().desc("themeId");
				     List<TableConfigBo> tableConfigBoList = dao().query(TableConfigBo.class, cri);
				     if(tableConfigBoList != null && tableConfigBoList.size()>0){
				    	 TableConfigBo maxTableConfigBo = tableConfigBoList.get(0);
					     tableConfigBo.setThemeId(maxTableConfigBo.getThemeId()+1);
				     }else{
				    	 //表中没数据时的处理
				    	 tableConfigBo.setThemeId(1);
				     }
				     
					//将新增的表配置信息 插入当前数据源的库表中
					dao().insert(tableConfigBo);//表配置信息是事先建好的吗
					Criteria cri2 = Cnd.cri();
				     cri2.getOrderBy().desc("colId");
				     List<ColumnConfigBo> columnConfigList = dao().query(ColumnConfigBo.class, cri2);
				     int maxId = 0;
				     if(columnConfigList != null && columnConfigList.size()>0){
				    	 ColumnConfigBo maxColumnConfig = columnConfigList.get(0);
					     maxId = maxColumnConfig.getColId()+1;
				     }else{
				    	 maxId = 1;
				     }
				    //将新增表的字段信息存入字段信息配置表中
					for (ColumnConfigBo tableColumn : columnList){
						tableColumn.setThemeId(tableConfigBo.getThemeId());
						tableColumn.setColumnType("1");
						tableColumn.setColId(maxId);
						maxId = maxId+1;
						dao().insert(tableColumn);
					}
					
					this.createTable(tableConfigBo, columnList);
				} 
				else {
					//修改表
					//原表主键
					Set<String> pkSetOld = new HashSet<String>();
					//修改后主键
					Set<String> pkSetNew = new HashSet<String>();
					//最终执行的SQL猎豹
					List<String> sqlList = new ArrayList<String>();
					//取出旧的表数据
					TableConfigBo queryThemeOld = dao().fetch(TableConfigBo.class,tableConfigBo.getThemeId());
					
					String ifTableSql = "select count(*) from information_schema.tables where table_name = '" + queryThemeOld.getViewName() + "'";
					
					Sql exeIfTableSql = Sqls.create(ifTableSql);
					exeIfTableSql.setCallback(new SqlCallback(){
						@Override
						public Object invoke(Connection conn, ResultSet rs, Sql sql)
								throws SQLException {
							int result = 0 ;
							while (rs.next()){
								result = rs.getInt(1);
							}
							return result;
						}
				    });
				    dao().execute(exeIfTableSql);
				    
				    int ifTable = (Integer)exeIfTableSql.getResult();
				    if(ifTable == 1){
					
						//是否更新表名
						if (!queryThemeOld.getViewName().equals(tableConfigBo.getViewName())){
							sqlList.add("alter table " + queryThemeOld.getViewName() + " rename " + tableConfigBo.getViewName());
						}						
						dao().update(tableConfigBo);
						
						
						
						//循环表中每个列
						int maxId = 0;
						Criteria cri2 = Cnd.cri();
	                    cri2.getOrderBy().desc("colId");
	                    List<ColumnConfigBo> columnConfigList = dao().query(ColumnConfigBo.class, cri2);
	                    if(columnConfigList != null && columnConfigList.size()>0){
	                    	ColumnConfigBo maxColumnConfig = columnConfigList.get(0);
	 	                    maxId = maxColumnConfig.getColId()+1;
	                    }else{
	                    	maxId = 1;
	                    }
						for (ColumnConfigBo columnConfig : columnList){
							columnConfig.setThemeId(tableConfigBo.getThemeId());
							columnConfig.setColumnType("1");
							if (columnConfig.getColId() != null){
								//修改列
								ColumnConfigBo queryColumnOld = dao().fetch(ColumnConfigBo.class, columnConfig.getColId());
								//是否旧主键
								if ("1".equals(queryColumnOld.getIsPk())){
									pkSetOld.add(queryColumnOld.getNameLetter());
								}
								
								//是否改列名
								if (!queryColumnOld.getNameLetter().equals(columnConfig.getNameLetter())){							
									String sqlTmp = "alter table " 
													+ tableConfigBo.getViewName() 
													+ " change column " 
													+ queryColumnOld.getNameLetter() + " "
													+ columnConfig.getNameLetter() + " ";
//													+ columnConfig.getColType() +"("
//													+ queryColumnOld.getColLength() + ")";
									if("1".equals(columnConfig.getColType())){
										sqlTmp = sqlTmp+ " varchar("
												+ columnConfig.getColLength() +") ";
									}
									else if("2".equals(columnConfig.getColType())){
										sqlTmp = sqlTmp + " datetime";
									}
									else{
										sqlTmp = sqlTmp + " int";
									}
									
									sqlList.add(sqlTmp);
								}
								
								//是否改列长度
								if ("1".equals(columnConfig.getColType()) && !queryColumnOld.getColLength().equals(columnConfig.getColLength())){
								    String sqlTmp = "alter table " 
								    				+ tableConfigBo.getViewName() 
								    				+ " modify " 
								    				+ columnConfig.getNameLetter() + " "
								    				+ columnConfig.getColType() + "(" 
								    				+ columnConfig.getColLength() + ")";
									sqlList.add(sqlTmp);
								}
								
								
								//是否改列描述
								if (!queryColumnOld.getName().equals(columnConfig.getName())){	
								    String sqlTmp1 = "alter table " 
								    				+ tableConfigBo.getViewName()
								    				+ " modify column "
								    				+ columnConfig.getNameLetter()
								    				+ columnConfig.getColType() + "("
								    				+ columnConfig.getColLength() + ")"
								    				+ " comment " 
								    				+ " '"+ columnConfig.getName() + "'";								
									sqlList.add(sqlTmp1);
								}
								
								//是否新主键
								if ("1".equals(columnConfig.getIsPk())){
									pkSetNew.add(columnConfig.getNameLetter());
								}
								dao().update(columnConfig);
							} 
							else {
							     
								//新增列
								 
							    String sqlTmp = "alter table " 
							    				+ tableConfigBo.getViewName() 
							    				+ " add column " 
							    				+ columnConfig.getNameLetter() + " "; 
//							    				+ columnConfig.getColType() + "("
//							    				+ columnConfig.getColLength() + ") "
//							    				+ " comment "
//							    				+  columnConfig.getNameLetter();
							    
								if ("1".equals(columnConfig.getColType())){
//									sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1023), columnConfig.getColType()) + "(" + columnConfig.getColLength()+ ") ";
									
									sqlTmp = sqlTmp + "varchar"
											+ "(" 
											+ columnConfig.getColLength()+ 
											") ";
								} 
								else if("2".equals(columnConfig.getColType())){
									sqlTmp = sqlTmp + "datetime";
								}
								else{
									sqlTmp = sqlTmp + "int";
								}
								
//								else {
//									sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1023), columnConfig.getColType());
//								}
//								sqlTmp = sqlTmp + ") ";
								//sqlTmp = sqlTmp + " " + "comment '" + columnConfig.getName() + "'";
								
								sqlTmp = sqlTmp
										+ "comment " 
										+ "'" + columnConfig.getNameLetter()+ "'";
								
								if ("1".equals(columnConfig.getIsPk())){
									pkSetNew.add(columnConfig.getNameLetter());
								}
								sqlList.add(sqlTmp);
//								sqlList.add(sqlTmp1);
								columnConfig.setColId(maxId);
								maxId += 1;
								dao().insert(columnConfig);
							}
						}
						//新旧主键判断
						if (!pkSetOld.equals(pkSetNew)){
							sqlList.add("alter table " + tableConfigBo.getViewName() + " drop primary key");
							String sql = "alter table " + tableConfigBo.getViewName() + " add primary key(";
							for (String columnName : pkSetNew){
								sql = sql + columnName + ",";
							}
							sql = sql.substring(0, sql.length()-1);
							sql = sql + ")";
							sqlList.add(sql);
						}
						this.modifyTable(sqlList);
				    }else if(ifTable == 0){
				    	this.createTable(tableConfigBo, columnList);
				    }
				}
			}
			public void createTable(final TableConfigBo tableConfigBo, final List<ColumnConfigBo> columnList){
				Trans.exec(new Atom(){
					public void run() {
						Collections.sort(columnList, new OrderComparator());
						//生成新表SQL语句
						String sql = "";
						sql = "create table ";
						sql = sql + tableConfigBo.getViewName();
						sql = sql + " (";
						for (ColumnConfigBo columnConfig : columnList){
							sql = sql + columnConfig.getNameLetter() + " ";
							if ("1".equals(columnConfig.getColType())){
								sql = sql + "varchar(" + columnConfig.getColLength() + ")" + " ";
							} else if("2".equals(columnConfig.getColType())) {
								sql = sql  + " datetime ";
							} else {
								sql = sql  + " int ";
							}
							if ("1".equals(columnConfig.getIsPk())){
								sql = sql +" not null ";
							}
							sql = sql + "comment" + " '" + columnConfig.getName() + "'";
							sql = sql + ",";
						}
//						if ("1".equals(TableConfigBo.getIfAutoCreate())){
//							sql = sql + " BELONG_TOWN varchar(100) , ";
//							sql = sql + " BELONG_COMMUNITY varchar(100) ,";
//							sql = sql + " TASK_NO varchar(64) , ";
//						}
						String pk = "";
						for (ColumnConfigBo columnConfig : columnList){
							if ("1".equals(columnConfig.getIsPk())){
								pk = pk + columnConfig.getNameLetter() + ",";
							}
						}
						if (pk.length() != 0){
							pk = pk.substring(0, pk.length()-1);
						}
						sql = sql  + "  primary key (" + pk + ")"; 
						sql = sql + ");";
						Sql exeSql = Sqls.create(sql);
						dao().execute(exeSql);
						
//						for (ColumnConfig columnConfig : columnList){
//							String sqlTmp1 = "COMMENT ON COLUMN " + TableConfigBo.getViewName() + "." + columnConfig.getNameLetter() + " is '"  + columnConfig.getName() + "'";
//							Sql commentSql = Sqls.create(sqlTmp1);
//							dao().execute(commentSql);
//						}
						
					}
					
				});
				
			}
			
			
			public void modifyTable(final List<String> sqlList){
				Trans.exec(new Atom(){
					
					public void run() {
						for (String sql : sqlList){
							Sql exeSql = Sqls.create(sql);
							dao().execute(exeSql);
						}
					}
				});
			}
		});
		
	}
	/**
	 * 反向生成
	 * @param viewName 数据库表名
	 */
	public void createDataByTableName(final String viewName){
		//确定该表是否存在
		String existsSqlStr = "select 1 from information_schema.tables where table_name = '"+viewName+"'";
		Sql existsSql = Sqls.create(existsSqlStr);
		existsSql.setCallback(new SqlCallback(){
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				String result = "";
				while (rs.next()){
					result = rs.getString(1);
				}
				return result;
			}
		});
		dao().execute(existsSql);
		String result = (String) existsSql.getResult();
		if (StringUtils.isEmpty(result)){
			return;
		}
		Trans.exec(new Atom(){
			@SuppressWarnings("unchecked")
			public void run(){
//				String tableNameStr = "select comments from all_tab_comments where table_name = upper('"+viewName+"')";
				//获取表描述
				String tableNameStr = "select table_comment from information_schema.tables where table_name = '"+viewName+"'";
				Sql tableNameSql = Sqls.create(tableNameStr);
				tableNameSql.setCallback(new SqlCallback(){
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
						String result = "";
						while (rs.next()){
							result = rs.getString(1);
						}
						return result;
					}
				});
				dao().execute(tableNameSql);
				String tableName = (String) tableNameSql.getResult();
				
				TableConfigBo TableConfigBo = new TableConfigBo();
				TableConfigBo.setCatalog("1");
				TableConfigBo.setName(tableName);
				TableConfigBo.setViewName(viewName);
				TableConfigBo.setValidity("1");
				
				Criteria cri = Cnd.cri();
			    cri.getOrderBy().desc("themeId");
			    List<TableConfigBo> TableConfigBoList = dao().query(TableConfigBo.class, cri);
			    if(TableConfigBoList != null && TableConfigBoList.size()>0){
			    	TableConfigBo maxTableConfigBo = TableConfigBoList.get(0);
				    TableConfigBo.setThemeId(maxTableConfigBo.getThemeId()+1);
			    }else{
			    	TableConfigBo.setThemeId(1);
			    }
			    //新增一条数据库配置信息
			    dao().insert(TableConfigBo);
			    //获取表名为viewName的表单字段信息
			    String colStr = 
			    		"select "
			    		+"t.COLUMN_NAME, "
			    		+"t.DATA_TYPE, "
			    		+"t.character_maximum_length, "
			    		+"t.column_comment "
			    		+"from information_schema.columns t  "
			    		+"where " 		
			    		+"table_name = '"+viewName+"'";
			    Sql colSql = Sqls.create(colStr);
			    colSql.setCallback(new SqlCallback(){
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
						List<Map<String,String>> result = new ArrayList<Map<String,String>>();
						while (rs.next()){
							Map<String,String> colMap = new HashMap<String,String>();
							colMap.put("columnName", rs.getString("COLUMN_NAME"));
							colMap.put("comments", rs.getString("COLUMN_COMMENT"));
							colMap.put("dataType", rs.getString("DATA_TYPE"));
							colMap.put("dataLength", rs.getString("CHARACTER_MAXIMUM_LENGTH"));
							result.add(colMap);
						}
						return result;
					}
			    });
			    dao().execute(colSql);
			    List<Map<String,String>> colMapList = (List<Map<String, String>>) colSql.getResult();
			    
			    Criteria cri2 = Cnd.cri();
			    cri2.getOrderBy().desc("colId");
			    List<ColumnConfigBo> columnConfigList = dao().query(ColumnConfigBo.class, cri2);
			    int maxId = 0;
			    if(columnConfigList != null && columnConfigList.size()>0){
			    	ColumnConfigBo maxColumnConfig = columnConfigList.get(0);
				    maxId = maxColumnConfig.getColId()+1;
			    }else{
			    	maxId = 1;
			    }
			    int i=1;
			    for (Map<String,String> map : colMapList){
			    	ColumnConfigBo colConfig = new ColumnConfigBo();
			    	colConfig.setColId(maxId);//主键
			    	maxId++;
			    	colConfig.setThemeId(TableConfigBo.getThemeId());//表ID    			    	
			    	colConfig.setName(map.get("columnName"));//描述    	
			    	colConfig.setNameLetter(map.get("columnName"));//字段名
			    	colConfig.setColLength(map.get("dataLength")); //字长度
			    	//********设置编辑类型和数据库字段类型//此处仅仅为mysql设置  需要适配*************************
			    	if ("datetime".equals(map.get("dataType"))){
			    		colConfig.setEditType("3");//编辑类型；3为日期型
			    		colConfig.setColType("2"); //2 datetime
			    		colConfig.setColTypeName(map.get("dataType"));
			    	} else if ("int".equals(map.get("dataType"))){
			    		colConfig.setEditType("5");//编辑类型；5为数值
			    		colConfig.setColType("3"); //3 int
			    		colConfig.setColTypeName(map.get("dataType"));
			    	}
			    	else if("varchar".equals(map.get("dataType"))) {
			    		colConfig.setEditType("1");//编辑类型；1为字符型；
			    		colConfig.setColType("1"); //1 varchar
			    		colConfig.setColTypeName(map.get("dataType"));
			    	}else if("text".equals(map.get("dataType"))){
			    		colConfig.setEditType("2");//编辑类型；2为文本型；
			    		colConfig.setColType("4"); // 4 text
			    		colConfig.setColTypeName(map.get("dataType"));
			    	}
			    	//如果不考虑重构，可以继续通过上述方法 设置Oracle的字段类型
			    	//设置字段类型，1为自定义，4、5、6为默认添加字段 
			    	if (map.get("columnName").equals("BELONG_TOWN")){
			    		colConfig.setColumnType("5");
			    	} else if (map.get("columnName").equals("BELONG_COMMUNITY")){
			    		colConfig.setColumnType("6");
			    	} else if (map.get("columnName").equals("TASK_NO")){
			    		colConfig.setColumnType("7");
			    	} else {
			    		colConfig.setColumnType("8");//字段类型;9表示手动添加字段；
			    	}
			    	colConfig.setValidity("1");//设置有效性
			    	colConfig.setIsPk("0");//设置主键
			    	if ("varchar".equals(map.get("dataType"))){
			    		colConfig.setColLength(map.get("dataLength"));
			    	}
			    	colConfig.setOrderNum(i);
			    	i++;
			    	dao().insert(colConfig);
			    }
			    
			    String pkSqlStr = 
			    		"select column_name "
			    		+"from information_schema.key_column_usage a "		    
			    		+"where a.table_name = '"+viewName+"'";
			    Sql pkSql = Sqls.create(pkSqlStr);
			    pkSql.setCallback(new SqlCallback(){
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
						List<String> result = new ArrayList<String>();
						while (rs.next()){
							result.add(rs.getString(1));
						}
						return result;
					}
			    });
			    dao().execute(pkSql);
			    List<String> pkList = (List<String>) pkSql.getResult();
			    for (String pkName : pkList){
			    	ColumnConfigBo column = dao().fetch(ColumnConfigBo.class,Cnd.where("themeId","=",TableConfigBo.getThemeId()).and("nameLetter","=",pkName));
			    	if (column != null){
			    		column.setIsPk("1");
			    		dao().update(column);
			    	}
			    }
			}
		});
	}
	
	/**
	 * 根据数据源和表名生成表配置和字段配置  待优化。。。。2017-05-10孟振乾
	 * @param dataSouceId
	 * @param viewName
	 * @param tableCatalog
	 */
	public void createDataByTableNameTest(final String dataSourceId,final String viewName,final String tableCatalog,final String tableComment){
		//开启事物
		Trans.exec(new Atom(){
			public void run(){
				//获取表描述
				TableConfigBo tableConfig = new TableConfigBo();
				
				try {
						tableConfig = executor.getTableConfigBoBean(viewName, dataSourceId);
						if(tableComment != null){
							tableConfig.setName(tableComment);
						}
						tableConfig.setCatalog(tableCatalog);
						tableConfig.setValidity("1");
					
					//将库表配置信息插入表配置表中
					Criteria cri = Cnd.cri();
				    cri.getOrderBy().desc("themeId");
				    List<TableConfigBo> TableConfigBoList = dao().query(TableConfigBo.class, cri);
				    if(TableConfigBoList != null && TableConfigBoList.size()>0){
				    	TableConfigBo maxTableConfigBo = TableConfigBoList.get(0);
				    	tableConfig.setThemeId(maxTableConfigBo.getThemeId()+1);
				    }else{
				    	tableConfig.setThemeId(1);
				    }
				    //新增一条数据库配置信息
				    dao().insert(tableConfig);//表配置插入成功
				    
				    //将对应的字段配置信息插入字段配置表中
				    
				    //获取表名为viewName的表单字段信息
				    List<Map<String,String>> colMapList = executor.getColConfigList(viewName, dataSourceId);
				    
				    Criteria cri2 = Cnd.cri();
				    cri2.getOrderBy().desc("colCfgId");
				    List<ColConfigBo> colConfigList = dao().query(ColConfigBo.class, cri2);
				    int maxId = 0;
				    if(colConfigList != null && colConfigList.size()>0){
				    	ColConfigBo maxColumnConfig = colConfigList.get(0);
					    maxId = maxColumnConfig.getColCfgId()+1;
				    }else{
				    	maxId = 1;
				    }
				    int i=1;
				    for (Map<String,String> map : colMapList){
				    	ColConfigBo colConfig = new ColConfigBo();
				    	colConfig.setColCfgId(maxId);//主键
				    	maxId++;
				    	colConfig.setColNameEn(map.get("COLNAME"));//字段名称
				    	colConfig.setColComment(map.get("COLCOMMENT"));//字段注释  
				    	colConfig.setColType(map.get("COLTYPE"));//字段类型加长度
				    	colConfig.setColTypeName(map.get("COLTYPENAME"));
				    	colConfig.setThemeId(tableConfig.getThemeId());//表ID    			    	
				    	colConfig.setValidity("1");
				    	//********设置编辑类型和数据库字段类型//此处仅仅为mysql设置  需要适配*************************
				    	if("varchar".equalsIgnoreCase(map.get("COLTYPENAME")) || "varchar2".equalsIgnoreCase(map.get("COLTYPENAME"))|| "char".equalsIgnoreCase(map.get("COLTYPENAME"))) {
				    		colConfig.setEditType("1");//编辑类型；1为字符型；
				    		
				    	}
				    	 else if ("int".equalsIgnoreCase(map.get("COLTYPENAME")) || "number".equalsIgnoreCase(map.get("COLTYPENAME"))){
				    		colConfig.setEditType("2");//编辑类型；2为数值
				    		
				    	}
				    	else if ("datetime".equalsIgnoreCase(map.get("COLTYPENAME")) || "date".equalsIgnoreCase(map.get("COLTYPENAME")) ){//未枚举全部时间类型
				    		colConfig.setEditType("3");//3编辑类型  ：日期；
				    		
				    	} else if("text".equalsIgnoreCase(map.get("COLTYPENAME")) || "blob".equalsIgnoreCase(map.get("COLTYPENAME")) || "clob".equalsIgnoreCase(map.get("COLTYPENAME"))){
				    		colConfig.setEditType("4");//编辑类型；4为文本型；
				    		
				    	}
				    	//设置主键
				    	if("PRI".equalsIgnoreCase(map.get("ISPK")) || map.get("COLNAME").equalsIgnoreCase(map.get("ISPK"))){
				    		colConfig.setIsPk("1");
				    	}
				    	
				    	//如果不考虑重构，可以继续通过上述方法 设置Oracle的字段类型
				    	//设置字段类型，1为自定义，4、5、6为默认添加字段 
				    	//if (map.get("columnName").equals("BELONG_TOWN")){
				    	//	colConfig.setColumnType("5");
				    	//} else if (map.get("columnName").equals("BELONG_COMMUNITY")){
				    	//	colConfig.setColumnType("6");
				    	//} else if (map.get("columnName").equals("TASK_NO")){
				    	//	colConfig.setColumnType("7");
				    	//} else {
				    	//	colConfig.setColumnType("8");//字段类型;9表示手动添加字段；
				    	//}
				    	//colConfig.setValidity("1");//设置有效性
				    	//colConfig.setIsPk("0");//设置主键
				    	//if ("varchar".equals(map.get("dataType"))){
				    	//	colConfig.setColLength(map.get("dataLength"));
				    	//}
				    	colConfig.setOrderNum(i);
				    	i++;
				    	dao().insert(colConfig);
				    }
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			   
			}
		});
	}
	
	/**
	 * 确定表存在 11/23
	 * @param viewName
	 * @return
	 */
	public Boolean isExist(final String viewName){
		Boolean flag = false;
		String existsSqlStr = "select 1 from information_schema.tables where table_name = '"+viewName+"'";
		Sql existsSql = Sqls.create(existsSqlStr);
		existsSql.setCallback(new SqlCallback(){
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				String result = "";
				while (rs.next()){
					result = rs.getString(1);
				}
				return result;
			}
		});
		dao().execute(existsSql);
		String result = (String) existsSql.getResult();
		if(result == null || StringUtils.isEmpty(result)){
			return flag;
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 读数据库表，根据库表viewName和数据源id读取库表表信息 11/24
	 * @param viewName
	 * @return
	 */
	public TableConfigBo getTabConfig(final String viewName,String dataSouceId){
		DBinfoBo dbInfo = dao().fetch(DBinfoBo.class, dataSouceId);//此处看的不爽
		TableConfigBo tableConfigBo = null;
		String tableNameStr = "select table_comment from information_schema.tables where table_name = '"+viewName+"'";
		if(isExist(viewName)){
			//获取表描述
			Sql tableNameSql = Sqls.create(tableNameStr);
			tableNameSql.setCallback(new SqlCallback(){
				@Override
				public Object invoke(Connection conn, ResultSet rs, Sql sql)
						throws SQLException {
					String result = "";
					while (rs.next()){
						result = rs.getString(1);
					}
					return result;
				}
			});
			//(new NutDao(DBAdapter.getDataSource(dbInfo))).execute(colSql);
			dao().execute(tableNameSql);
			String tableName = (String) tableNameSql.getResult();
			tableConfigBo = new TableConfigBo();
			//tableConfigBo.setCatalog("1");
			tableConfigBo.setName(tableName);
			tableConfigBo.setViewName(viewName.toUpperCase());
			tableConfigBo.setValidity("1");
			//tableConfigBo.setIfAutoCreate("0");
		}
		return tableConfigBo;
	}
	
	/**
	 * 读数据库表，根据数据源id读取库表英文名集合
	 * @param viewName
	 * @param dataSouceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSchemTabNameList(String dataSouceId){
		List<String> tabNameList = new ArrayList<String>();
		/*DBinfoBo dbInfo = dao().fetch(DBinfoBo.class, dataSouceId);//此处看的不爽
		if(dbInfo == null){
			return tabNameList;
		}
		Dao executDao = new NutDao(DBAdapter.getDataSource(dbInfo));
		if(executDao == null){
			return tabNameList;
		}*/
		//String sql1 = " SELECT UPPER(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = " + dbInfo.getDbEnName();
		String sql = " SELECT UPPER(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'ptsjzx' ";
		Sql executSql = Sqls.create(sql);
		executSql.setCallback(new SqlCallback(){

			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while(rs.next()){
					result.add(rs.getString(1));
				}
				return result;
			}
			
		});
		
		//executDao.execute(executSql);
		//(new NutDao(DBAdapter.getDataSource(dbInfo))).execute(executSql);
		dao().execute(executSql);
		tabNameList = (List<String>) executSql.getResult();
		return tabNameList;
		
	}
	
	/**
	 * 根据库表viewName和数据源id读取库表字段信息 11/24
	 * @param viewName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String,String>> getMapListColumn(final String viewName,String dataSouceId){
//		DBinfoBo dbInfo = dao().fetch(DBinfoBo.class, dataSouceId);//此处看的不爽
//		DBAdapter.getDataSource(dbInfo);
		//获取表名为viewName的表单字段信息
	    String colStr = 
	    		"select "
	    		+"t.COLUMN_NAME, "
	    		+"t.DATA_TYPE, "
	    		+"t.character_maximum_length, "
	    		+"t.column_comment "
	    		+"from information_schema.columns t  "
	    		+"where " 		
	    		+"table_name = '"+viewName+"'";
	    Sql colSql = Sqls.create(colStr);
	    colSql.setCallback(new SqlCallback(){
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<Map<String,String>> result = new ArrayList<Map<String,String>>();
				while (rs.next()){
					Map<String,String> colMap = new HashMap<String,String>();
					colMap.put("columnName", rs.getString("COLUMN_NAME").toUpperCase());
					colMap.put("comments", rs.getString("COLUMN_COMMENT"));
					colMap.put("dataType", rs.getString("DATA_TYPE"));
					colMap.put("dataLength", rs.getString("CHARACTER_MAXIMUM_LENGTH"));
					result.add(colMap);
				}
				return result;
			}
	    });
	    //(new NutDao(DBAdapter.getDataSource(dbInfo))).execute(colSql);
	    dao().execute(colSql);//正常使用时，可以改写为new
	    List<Map<String,String>> colMapList = (List<Map<String, String>>) colSql.getResult();
		return colMapList;
	}
	
	/**
	 * 根据库表viewName和数据源id获取字段集合和字段name集合  11/23
	 * @param viewName
	 * @return
	 */
	public Map<String,Object> getColList(final String viewName,String dataSourceId){
		List<Map<String,String>> colMapList	= getMapListColumn(viewName, dataSourceId);
		List<String> colNameList = new ArrayList<String>();
		Map<String,Object> result = new HashMap<String,Object>(); 
		 for (Map<String,String> map : colMapList){
			 	colNameList.add(map.get("columnName").toUpperCase());
		    	ColumnConfigBo colConfig = new ColumnConfigBo();
		    	colConfig.setNameLetter(map.get("columnName").toUpperCase());//字段名
		    	colConfig.setName(map.get("comments"));						 //描述    
		    	colConfig.setColTypeName(map.get("dataType"));			     //字段类型
		    	colConfig.setColLength(map.get("dataLength"));               //字长度
		    	
		    	if("varchar".equals(map.get("dataType"))) {
		    		colConfig.setEditType("1");                              //编辑类型；1为字符型；
		    		colConfig.setColType("1");                               //1. varchar
		    	}else if("text".equals(map.get("dataType"))){
		    		colConfig.setEditType("2");                             //编辑类型；2为文本型；
		    		colConfig.setColType("4");                              // 4 .text
		    	}else if ("datetime".equals(map.get("dataType"))){
		    		colConfig.setEditType("3");                             //编辑类型；3为日期型
		    		colConfig.setColType("2"); 								// 2 .datetime
		    	} 
		    	 else if ("int".equals(map.get("dataType"))){
		    		colConfig.setEditType("4");                             //编辑类型；4为数值
		    		colConfig.setColType("3");                              // 3 .int
		    	}
		 }
		result.put("colNameList", colNameList) ;
		result.put("colNameList", colNameList) ;
		return result;
		
	}
	
	/**
	 * 根据库表viewName获取主键字段 11/23
	 * @param viewName
	 * @return
	 */
	public List<String> getPkList(final String viewName){
		String pkSqlStr = 
    		"select column_name "
    		+"from information_schema.key_column_usage a "		    
    		+"where a.table_name = '"+viewName+"'";
	    Sql pkSql = Sqls.create(pkSqlStr);
	    pkSql.setCallback(new SqlCallback(){
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while (rs.next()){
					result.add(rs.getString(1));
				}
				return result;
			}
	    });
	    dao().execute(pkSql);
	    @SuppressWarnings("unchecked")
		List<String> pkList = (List<String>) pkSql.getResult();
		return pkList;
		
	}
	
	/**
	 * 读table配置表，根据数据源id获取表配置集合（用于筛选供选择的表）
	 * @param viewName
	 * @param dataSourceId
	 * @return
	 */
	public List<TableConfigBo> getTabConfigList(String dataSourceId){
		List<TableConfigBo> tabConfigList=null;
		Criteria cri = Cnd.cri();
		cri.where().and("DATA_SOURCE_ID", "=", dataSourceId);
		tabConfigList = (List<TableConfigBo>) dao().query(TableConfigBo.class, cri);
		return tabConfigList;
	}
	
	/**
	 * 根据数据源id获取表配置中表名集合（用于筛选供选择的表，备用）
	 * @param viewName
	 * @param dataSourceId
	 * @return
	 */
	public List<String> getTabConfNameList(String dataSourceId){
		List<TableConfigBo> tabConfigList = getTabConfigList(dataSourceId);
		List<String> tablNameList = null;
		if(tabConfigList != null && tabConfigList.size()>0){
			tablNameList = new ArrayList<String>();
			for(TableConfigBo table:tabConfigList){
				tablNameList.add(table.getViewName().toUpperCase());
			}
		}
		return tablNameList;
		
	}
	
	/**
	 * 读数据库表，根据数据源id读取库表英文名集合
	 * @param viewName
	 * @param dataSouceId
	 * @return
	 * @throws Exception 
	 * 注意：此时的结果是大写的字符串
	 */
	public List<String> getSchemTabNameListTest(String dataSourceId) throws Exception{
		List<Map<String,String>> mapTabNameList = null;
		List<String> tableNameList = null;
		DBinfoBo dbInfo = dao().fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		String mysqlSql = " SELECT UPPER(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'wddc' ";
		String oracleSql = "select a.TABLE_NAME from user_tables a order by TABLE_NAME ";
		if(dbInfo != null){
			mapTabNameList = new ArrayList<Map<String,String>>();
			dbPool.createDataSource(dbInfo);
			mapTabNameList = executor.getListMapResult(oracleSql, dataSourceId);
		}
		if(mapTabNameList != null && mapTabNameList.size()>0){
			tableNameList = new ArrayList<String>();
			for(Map<String,String> map:mapTabNameList){
				String tableName = (String) map.get("TABLE_NAME");
				tableNameList.add(tableName.toUpperCase());
			}
		}
		return tableNameList;
		
	}
	
	/**
	 * 读数据库表，根据库表viewName和数据源id读取库表表信息 12/15  ！！！！！！！！！！！！！！！！！！该方法仅仅用于测试使用！！！！！！！！！！！！！！！！！！！！！！！！！！
	 * @param viewName
	 * @return
	 * @throws Exception 
	 */
	public TableConfigBo getTabConfigTest(final String viewName,final String dataSourceId) throws Exception{
		DBinfoBo dbInfo = dao().fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		dbPool.createDataSource(dbInfo);
		TableConfigBo tableConfigBo = null;
		//获取表描述
		String tableNameStr = "select table_comment from information_schema.tables where table_name = '"+viewName+"'";
		Map<String,String> map = new HashMap<String,String>();
		map = executor.getMapResult(tableNameStr, dataSourceId);
			String tableName = map.get("table_comment");
			tableConfigBo = new TableConfigBo();
			tableConfigBo.setCatalog("1");
			tableConfigBo.setName(tableName);
			tableConfigBo.setViewName(viewName.toUpperCase());
			tableConfigBo.setValidity("1");
			//tableConfigBo.setIfAutoCreate("0");
		return tableConfigBo;
	}
	
	/**
	 * 根据库表viewName和数据源id读取库表字段信息 11/24
	 * @param viewName
	 * @return
	 * @throws Exception 
	 */
	public  List<Map<String,String>> getMapListColumnTest(final String viewName,String dataSourceId) throws Exception{
		List<Map<String,String>> colMapList = new ArrayList<Map<String,String>>();
		//获取表名为viewName的表单字段信息
	    String colStr = 
	    		"select "
	    		+"t.COLUMN_NAME, "
	    		+"t.DATA_TYPE, "
	    		+"t.character_maximum_length, "
	    		+"t.column_comment "
	    		+"from information_schema.columns t  "
	    		+"where " 		
	    		+"table_name = '"+viewName+"'";
	    colMapList = executor.getListMapResult(colStr, dataSourceId);
	   
		return colMapList;
	}
	
	/**
	 * 根据SQL语句生成表
	 * @param tableConfigBo
	 * @param columnList
	 */
	public void createTableTest( final String crTabSQL,final String dataSourceId){
		
		Trans.exec(new Atom(){
			public void run() {
				try {
					executor.dealSql(crTabSQL, dataSourceId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}
}
