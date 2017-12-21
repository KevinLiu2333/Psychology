package com.wonders.pzgl.service;

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
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.Service;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.wonders.pzgl.entity.ColumnConfig;
import com.wonders.pzgl.entity.TableConfig;
import com.wonders.tiles.dic.DicDataUtils;

@IocBean(fields = "dao")
public class PzglTableService extends Service{
	public void changeTable(final TableConfig tableConfig, final List<ColumnConfig> columnList){
		Trans.exec(new Atom(){
			
			public void run(){
				tableConfig.setValidity("1");
				if (tableConfig.getThemeId() == null){
				     Criteria cri = Cnd.cri();
				     cri.getOrderBy().desc("themeId");
				     List<TableConfig> tableConfigList = dao().query(TableConfig.class, cri);
				     TableConfig maxTableConfig = tableConfigList.get(0);
				     tableConfig.setThemeId(maxTableConfig.getThemeId()+1);
					//新增表
					dao().insert(tableConfig);
					//
					Criteria cri2 = Cnd.cri();
				     cri2.getOrderBy().desc("colId");
				     List<ColumnConfig> columnConfigList = dao().query(ColumnConfig.class, cri2);
				     ColumnConfig maxColumnConfig = columnConfigList.get(0);
				     int maxId = maxColumnConfig.getColId()+1;
					for (ColumnConfig tableColumn : columnList){
						tableColumn.setThemeId(tableConfig.getThemeId());
						tableColumn.setColumnType("1");
						tableColumn.setColId(maxId);
						maxId = maxId+1;
						dao().insert(tableColumn);
					}
					ColumnConfig columnConfigTmp = new ColumnConfig();
					columnConfigTmp.setThemeId(tableConfig.getThemeId());
					columnConfigTmp.setName("所属街镇");
					columnConfigTmp.setNameLetter("BELONG_TOWN");
					columnConfigTmp.setEditType("2");
					columnConfigTmp.setDicId(1036);
					columnConfigTmp.setColumnType("4");
					columnConfigTmp.setValidity("1");
					columnConfigTmp.setIsPk("0");
					columnConfigTmp.setColType("1");
					columnConfigTmp.setColLength("100");
					columnConfigTmp.setOrderNum(999);
					columnConfigTmp.setColId(maxId);
					maxId = maxId+1;
					dao().insert(columnConfigTmp);
					
					columnConfigTmp = new ColumnConfig();
					columnConfigTmp.setThemeId(tableConfig.getThemeId());
					columnConfigTmp.setName("所属居委会");
					columnConfigTmp.setNameLetter("BELONG_COMMUNITY");
					columnConfigTmp.setEditType("2");
					columnConfigTmp.setDicId(1037);
					columnConfigTmp.setColumnType("5");
					columnConfigTmp.setValidity("1");
					columnConfigTmp.setIsPk("0");
					columnConfigTmp.setColType("1");
					columnConfigTmp.setColLength("100");
					columnConfigTmp.setOrderNum(1000);
					columnConfigTmp.setColId(maxId);
					maxId = maxId+1;
					dao().insert(columnConfigTmp);
					
					columnConfigTmp = new ColumnConfig();
					columnConfigTmp.setThemeId(tableConfig.getThemeId());
					columnConfigTmp.setName("所属台账号");
					columnConfigTmp.setNameLetter("TASK_NO");
					columnConfigTmp.setEditType("1");
					columnConfigTmp.setColumnType("6");
					columnConfigTmp.setValidity("1");
					columnConfigTmp.setIsPk("0");
					columnConfigTmp.setColType("1");
					columnConfigTmp.setColLength("64");
					columnConfigTmp.setOrderNum(1001);
					columnConfigTmp.setColId(maxId);
					maxId = maxId+1;
					dao().insert(columnConfigTmp);
					
					this.createTable(tableConfig, columnList);
				} else {
					//修改表
					//原表主键
					Set<String> pkSetOld = new HashSet<String>();
					//修改后主键
					Set<String> pkSetNew = new HashSet<String>();
					//最终执行的SQL猎豹
					List<String> sqlList = new ArrayList<String>();
					//取出旧的表数据
					TableConfig queryThemeOld = dao().fetch(TableConfig.class,tableConfig.getThemeId());
					//是否更新表名
					if (!queryThemeOld.getViewName().equals(tableConfig.getViewName())){
						sqlList.add("alter table " + queryThemeOld.getViewName() + " rename to " + tableConfig.getViewName());
					}
					dao().update(tableConfig);
					//循环表中每个列
					Criteria cri2 = Cnd.cri();
                    cri2.getOrderBy().desc("colId");
                    List<ColumnConfig> columnConfigList = dao().query(ColumnConfig.class, cri2);
                    ColumnConfig maxColumnConfig = columnConfigList.get(0);
                    int maxId = maxColumnConfig.getColId()+1;
					for (ColumnConfig columnConfig : columnList){
						columnConfig.setThemeId(tableConfig.getThemeId());
						columnConfig.setColumnType("1");
						if (columnConfig.getColId() != null){
							//修改列
							ColumnConfig queryColumnOld = dao().fetch(ColumnConfig.class, columnConfig.getColId());
							//是否旧主键
							if ("1".equals(queryColumnOld.getIsPk())){
								pkSetOld.add(queryColumnOld.getNameLetter());
							}
							//是否改列名
							if (!queryColumnOld.getNameLetter().equals(columnConfig.getNameLetter())){
								//String sqlTmp = "alter table " + tableConfig.getViewName() + " change " + queryColumnOld.getNameLetter() + " " + columnConfig.getNameLetter() + " ";
							    String sqlTmp = "alter table " + tableConfig.getViewName() + " rename column " + queryColumnOld.getNameLetter() + " to " + columnConfig.getNameLetter() + " ";
							    //if ("1".equals(columnConfig.getColType())){
								//	sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1023), columnConfig.getColType()) + "(" + columnConfig.getColLength()+ ")";
								//} else {
								//	sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1023), columnConfig.getColType());
								//}
								
								sqlList.add(sqlTmp);
							}
							//是否改列长度
							if ("1".equals(columnConfig.getColType()) && !queryColumnOld.getColLength().equals(columnConfig.getColLength())){
								//String sqlTmp = "alter table " + tableConfig.getViewName() + " change " + columnConfig.getNameLetter() + " " + columnConfig.getNameLetter() + " varchar(" + columnConfig.getColLength() + ")";
							    String sqlTmp = "alter table " + tableConfig.getViewName() + " modify ( " + columnConfig.getNameLetter() + " varchar2(" + columnConfig.getColLength() + "))";
								sqlList.add(sqlTmp);
							}
							//是否改列描述
							if (queryColumnOld.getName()==null||!queryColumnOld.getName().equals(columnConfig.getName())){
								//String sqlTmp = "alter table " + tableConfig.getViewName() + " change " + columnConfig.getNameLetter() + " " + columnConfig.getNameLetter() + " ";
							    //if ("1".equals(columnConfig.getColType())){
								//	sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1023), columnConfig.getColType()) + "(" + columnConfig.getColLength()+ ")";
								//} else {
								//	sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1023), columnConfig.getColType());
								//}
								//sqlTmp = sqlTmp + " " + "comment '" + columnConfig.getName() + "'";
							    String sqlTmp1 = "COMMENT ON COLUMN " + tableConfig.getViewName() + "." + columnConfig.getNameLetter() + " is '"  + columnConfig.getName() + "'";
								//sqlList.add(sqlTmp);
								sqlList.add(sqlTmp1);
							}
							//是否新主键
							if ("1".equals(columnConfig.getIsPk())){
								pkSetNew.add(columnConfig.getNameLetter());
							}
							dao().update(columnConfig);
						} else {
						     
							//新增列
							//String sqlTmp = "alter table " + tableConfig.getViewName() + " add " + columnConfig.getNameLetter() + " ";
						    String sqlTmp = "alter table " + tableConfig.getViewName() + " add (" + columnConfig.getNameLetter() + " ";
							if ("1".equals(columnConfig.getColType())){
								sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1024), columnConfig.getColType()) + "(" + columnConfig.getColLength()+ ") ";
							} else {
								sqlTmp = sqlTmp + DicDataUtils.getInstance().getDicItemName(new Integer(1024), columnConfig.getColType());
							}
							sqlTmp = sqlTmp + ") ";
							//sqlTmp = sqlTmp + " " + "comment '" + columnConfig.getName() + "'";
							String sqlTmp1 = "COMMENT ON COLUMN " + tableConfig.getViewName() + "." + columnConfig.getNameLetter() + " is '"  + columnConfig.getName() + "'";
							if ("1".equals(columnConfig.getIsPk())){
								pkSetNew.add(columnConfig.getNameLetter());
							}
							sqlList.add(sqlTmp);
							sqlList.add(sqlTmp1);
							columnConfig.setColId(maxId);
							maxId += 1;
							dao().insert(columnConfig);
						}
					}
					//新旧主键判断
					if (!pkSetOld.equals(pkSetNew)){
						sqlList.add("alter table " + tableConfig.getViewName() + " drop primary key");
						String sql = "alter table " + tableConfig.getViewName() + " add primary key(";
						for (String columnName : pkSetNew){
							sql = sql + columnName + ",";
						}
						sql = sql.substring(0, sql.length()-1);
						sql = sql + ")";
						sqlList.add(sql);
					}
					this.modifyTable(sqlList);
				}
			}
			public void createTable(final TableConfig tableConfig, final List<ColumnConfig> columnList){
				Trans.exec(new Atom(){
					public void run() {
						Collections.sort(columnList, new OrderComparator());
						//生成新表SQL语句
						String sql = "";
						sql = "create table ";
						sql = sql + tableConfig.getViewName();
						sql = sql + " (";
						for (ColumnConfig columnConfig : columnList){
							sql = sql + columnConfig.getNameLetter() + " ";
							if ("1".equals(columnConfig.getColType())){
								sql = sql + DicDataUtils.getInstance().getDicItemName(new Integer(1024), columnConfig.getColType()) + "(" + columnConfig.getColLength() + ")" + " ";
							} else {
								sql = sql + DicDataUtils.getInstance().getDicItemName(new Integer(1024), columnConfig.getColType()) + " ";
							}
							if ("1".equals(columnConfig.getIsPk())){
								sql = sql +" not null ";
							}
							sql = sql +",";
						}
						if ("1".equals(tableConfig.getIfAutoCreate())){
							sql = sql + " BELONG_TOWN varchar2(100) , ";
							sql = sql + " BELONG_COMMUNITY varchar2(100) ,";
							sql = sql + " TASK_NO varchar2(64) , ";
						}
						String pk = "";
						for (ColumnConfig columnConfig : columnList){
							if ("1".equals(columnConfig.getIsPk())){
								pk = pk + columnConfig.getNameLetter() + ",";
							}
						}
						if (pk.length() != 0){
							pk = pk.substring(0, pk.length()-1);
						}
						sql = sql + "constraint PK_"+pk+"  primary key (" + pk + ")"; 
						sql = sql + ")";
						Sql exeSql = Sqls.create(sql);
						dao().execute(exeSql);
						for (ColumnConfig columnConfig : columnList){
							String sqlTmp1 = "COMMENT ON COLUMN " + tableConfig.getViewName() + "." + columnConfig.getNameLetter() + " is '"  + columnConfig.getName() + "'";
							Sql commentSql = Sqls.create(sqlTmp1);
							dao().execute(commentSql);
						}
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
	
	public void createDataByTableName(final String viewName,final String tableName){
		String existsSqlStr = "select 1 from user_tables where table_name='"+viewName+"'";
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
			public void run(){
				/*String tableNameStr = "select comments from all_tab_comments where table_name = upper('"+viewName+"')";
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
				});*/
				//dao().execute(tableNameSql);
				//String tableName = (String) tableNameSql.getResult();
				
				TableConfig tableConfig = new TableConfig();
				tableConfig.setCatalog("1");
				tableConfig.setName(tableName);
				tableConfig.setViewName(viewName);
				tableConfig.setValidity("1");
				tableConfig.setIfAutoCreate("1");
				Criteria cri = Cnd.cri();
			    cri.getOrderBy().desc("themeId");
			    List<TableConfig> tableConfigList = dao().query(TableConfig.class, cri);
			    if(tableConfigList.size()>0){
			    	TableConfig maxTableConfig = tableConfigList.get(0);
			    	tableConfig.setThemeId(maxTableConfig.getThemeId()+1);
			    }else 
			    {
			    	tableConfig.setThemeId(1);
			    }
			    dao().insert(tableConfig);
			    
			    String colStr = 
			    		"select "
			    		+"t.COLUMN_NAME, "
			    		+"t.DATA_TYPE, "
			    		+"t.DATA_LENGTH, "
			    		+"C.COMMENTS "
			    		+"from user_tab_columns t ,all_col_comments c "
			    		+"where " 
			    		+" T.COLUMN_NAME = C.COLUMN_NAME and T.TABLE_NAME = C.TABLE_NAME "
			    		+"and t.table_name = upper('"+viewName+"')";
			    Sql colSql = Sqls.create(colStr);
			    colSql.setCallback(new SqlCallback(){
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql)
							throws SQLException {
						List<Map<String,String>> result = new ArrayList<Map<String,String>>();
						while (rs.next()){
							Map<String,String> colMap = new HashMap<String,String>();
							colMap.put("columnName", rs.getString("COLUMN_NAME"));
							colMap.put("comments", rs.getString("COMMENTS"));
							colMap.put("dataType", rs.getString("DATA_TYPE"));
							colMap.put("dataLength", rs.getString("DATA_LENGTH"));
							result.add(colMap);
						}
						return result;
					}
			    });
			    dao().execute(colSql);
			    @SuppressWarnings("unchecked")
				List<Map<String,String>> colMapList = (List<Map<String, String>>) colSql.getResult();
			    Criteria cri2 = Cnd.cri();
			    cri2.getOrderBy().desc("colId");
			    List<ColumnConfig> columnConfigList = dao().query(ColumnConfig.class, cri2);
			    int maxId;
			    if(columnConfigList.size()>0)
			    {
			    	ColumnConfig maxColumnConfig = columnConfigList.get(0);
			    	maxId = maxColumnConfig.getColId()+1;
			    }
			    else {
					maxId=1;
				}
			    int i=1;
			    for (Map<String,String> map : colMapList){
			    	ColumnConfig colConfig = new ColumnConfig();
			    	colConfig.setColId(maxId);//主键
			    	maxId++;
			    	colConfig.setThemeId(tableConfig.getThemeId());//表ID
			    	colConfig.setName(map.get("comments"));//中文描述
			    	colConfig.setNameLetter(map.get("columnName"));//字段名
			    	//设置编辑类型和数据库字段类型
			    	if ("DATE".equals(map.get("dataType"))){
			    		colConfig.setEditType("3");//编辑类型；3为日期型
			    		colConfig.setColType("2");
			    	} else if ("NUMBER".equals(map.get("dataType"))){
			    		colConfig.setEditType("5");//编辑类型；3为日期型
			    		colConfig.setColType("3");
			    	}
			    	else {
			    		colConfig.setEditType("1");//编辑类型；无法判断是否为字典，暂时定为1；
			    		colConfig.setColType("1");
			    	}
			    	//设置字段类型，1为自定义，4、5、6为默认添加字段 
			    	if (map.get("columnName").equals("BELONG_TOWN")){
			    		colConfig.setColumnType("4");
			    	} else if (map.get("columnName").equals("BELONG_COMMUNITY")){
			    		colConfig.setColumnType("5");
			    	} else if (map.get("columnName").equals("TASK_NO")){
			    		colConfig.setColumnType("6");
			    	} else {
			    		colConfig.setColumnType("1");//字段类型;1表示手动添加字段；
			    	}
			    	colConfig.setValidity("1");//设置有效性
			    	colConfig.setIsPk("0");//设置主键
			    	if ("VARCHAR2".equals(map.get("dataType"))){
			    		colConfig.setColLength(map.get("dataLength"));
			    	}
			    	colConfig.setOrderNum(i);
			    	i++;
			    	dao().insert(colConfig);
			    }
			    
			    String pkSqlStr = 
			    		"select COLUMN_NAME from user_cons_columns a, user_constraints b "
			    		+"where a.constraint_name = b.constraint_name "
			    		+"and b.constraint_type = 'P' "
			    		+"and a.table_name = '"+viewName+"'";
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
			    for (String pkName : pkList){
			    	ColumnConfig column = dao().fetch(ColumnConfig.class,Cnd.where("themeId","=",tableConfig.getThemeId()).and("nameLetter","=",pkName));
			    	if (column != null){
			    		column.setIsPk("1");
			    		dao().update(column);
			    	}
			    }
			}
		});
	}
}
