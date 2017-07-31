/**
 * 
 */
package com.wonders.gis.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * @author XiaQiuQin
 * .net数据表映射
 */
public class DataTable {
	private List<Field> fields = new ArrayList<Field>();	//字段集
	private List<Row> data = new ArrayList<Row>();			//数据集
	
	/**
	 * 构造函数
	 */
	public DataTable(){
		
	}
	
	/** 构造函数
	 * @param fields 字段集
	 * @param data 数据集
	 */
	public DataTable(List<Field> fields, List<Row> data){
		if(fields != null){
			this.fields = fields;
		}
		if(data != null){
			this.data = data;
		}
	}
	
	/** 数据长度
	 * @return 数据记录数
	 */
	public int size(){
		return data.size();
	}
	
	/** 字段个数
	 * @return 字段个数
	 */
	public int fieldsize(){
		return fields.size();
	}
	
	/** 获取index的数据记录
	 * @param index 索引
	 * @return
	 */
	public Row get(int index){
		if(index >=0 && index < data.size()){
			return data.get(index);
		}
		return null;
	}
	
	/** 设置index的数据记录
	 * @param index 索引
	 * @param row 行数据
	 */
	public void set(int index, Row row){
		if(index >=0 && index < data.size()){
			data.set(index, row);
		}
	}
	
	/** 获取字段名称的索引
	 * @param fieldName 字段名称
	 * @return 字段名称索引
	 */
	public int getFieldIndex(String fieldName){
		return fields.indexOf(fieldName);
	}
	
	/** 获取行、列索引的数据
	 * @param rindex row index 行索引
	 * @param findex field index 列索引
	 * @return 行、列索引对应的数据
	 */
	public Object get(int rindex, int findex){
		if(rindex >=0 && rindex < data.size() && findex >=0 && findex < fields.size()){
			Row row = data.get(rindex);
			return row.get(findex);
		}
		return null;
	}
	
	/** 获取行索引、列名称的数据
	 * @param rindex row index 行索引
	 * @param fieldName 列名称
	 * @return 行索引、列名称的数据
	 */
	public Object get(int rindex, String fieldName){
		if(rindex >=0 && rindex < data.size()){
			Row row = data.get(rindex);
			return row.get(fieldName);
		}
		return null;
	}
	/** 获取字段对象
	 * @param index 字段索引
	 * @return 字段对象
	 */
	public Field getField(int index){
		if(index >=0 && index < fields.size()){
			return fields.get(index);
		}
		return null;
	}
	
	/** 获取字段对象数组
	 * @return 字段对象数组
	 */
	public Field[] getFields(){
		return (Field[])fields.toArray();
	}
	
	/** 验证字段名称是否可以加入
	 * @param field 字段名称
	 * @return 字段名称是否可以加入
	 */
	private boolean validField(String field){
		boolean flag = false;
		if(field != null ){
			field = field.trim();
			if(field.length() > 0){
				int fldCount = fields.size();
				String fieldName = "";
				for(int i = 0; i < fldCount; i++){
					fieldName = fields.get(i).getFieldName();
					if(fieldName.equalsIgnoreCase(field)){
						return false;
					}
				}
				flag = true;
			}
		}
		return flag;
	}
	
	/** 添加字段
	 * @param fld 字段对象
	 */
	public void addField(Field fld){
		if(fld != null){
			if(validField(fld.getFieldName())){
				fields.add(fld);
				for(Row row: data){
					row.add(fld, null);
				}
			}
		}
	}
	
	/** 添加字段
	 * @param fieldName 字段名称
	 */
	public void addField(String fieldName){
		if(fieldName != null){
			if(validField(fieldName)){
				Field fld = new Field(fieldName);
				fields.add(fld);
				for(Row row: data){
					row.add(fld, null);
				}
			}
		}
	}
	
	/** 添加字段集
	 * @param flds 字段集
	 */
	public void addFields(List<String> flds){
		if(flds != null && flds.size() > 0){
			for(String fld : flds){
				addField(fld);
			}
		}
	}
	
	/** 添加字段集
	 * @param flds 字段数组
	 */
	public void addFields(Field[] flds){
		if(flds != null && flds.length > 0){
			for(Field fld : flds){
				addField(fld);
			}
		}
	}
	
	/** 添加行数据
	 * @param row 行数据
	 */
	public void add(Row row){
		if(row != null)
			data.add(row);
	}
	
	/** 添加行
	 * @param row 行数据
	 */
	public void add(Object[] row){
		if(row != null)
			if(row.length == fields.size())
				data.add(new Row(fields, Arrays.asList(row)));
	}
	
	/** 添加行
	 * @param row 行数据
	 */
	public void add(Hashtable<String, Object> row){
		if(row != null){
			List<Object> rowData = new ArrayList<Object>();
			for(int j = 0; j < fields.size(); j++){
				String key = fields.get(j).getFieldName();
				if(row.containsKey(key)){
					rowData.add(row.get(key));
				}
				else{
					rowData.add(null);
				}
			}
			data.add(new Row(fields, rowData));
		}
	}
	
	/** 移除行
	 * @param index 行索引
	 */
	public void remove(int index){
		if(index >= 0 &&  index < data.size())
			data.remove(index);
	}
	
	/** 移除行
	 * @param row 行对象
	 */
	public void remove(Row row){
		if(row != null)
			data.remove(row);
	}
	
	/** 清除数据
	 * 
	 */
	public void clear(){
		data.clear();
	}
	
	/** 转换为数组
	 * @return 行集数组
	 */
	public Row[] toArray(){
		return (Row[])data.toArray();
	}
	
	
	/** 将键值对的集合的list数据转换为 DataTable
	 * @param rs 键值对的集合的list数据
	 * @return DataTable
	 */
	public static DataTable toDataTable(List<Hashtable<String, Object>> rs){
		DataTable dt = null;
		if(rs!= null){
			dt = new DataTable();
			List<Field> fields = new ArrayList<Field>();
			Field fld = null;
			Row row = null;
			List<Object> data;
			if(rs.size() > 0){
				Hashtable<String, Object> item = rs.get(0);
				for(String key: item.keySet()){
					fld = new Field(key);
					fields.add(fld);
				}
				dt.fields = fields;
				for(int i = 0; i < rs.size(); i++){
					item = rs.get(i);
					data = new ArrayList<Object>();
					for(int j = 0; j < fields.size(); j++){
						String key = fields.get(j).getFieldName();
						if(item.containsKey(key)){
							data.add(item.get(key));
						}
						else{
							data.add(null);
						}
						
					}
					row = new Row(fields, data);
					dt.data.add(row);
				}
			}
			
			
		}
		return dt;
	}
	
	/** 将 ResultSet转换为DataTable
	 * @param rs ResultSet
	 * @return DataTable
	 */
	public static DataTable toDataTable(ResultSet rs){
		DataTable dt = null;
		if(rs!= null){
			dt = new DataTable();
			try {
				ResultSetMetaData md = rs.getMetaData();
				int count = md.getColumnCount();
				List<Field> fields = new ArrayList<Field>();
				Field fld = null;
				Row row = null;
				List<Object> data;
				for(int i = 0; i < count; i++){
					String name = md.getColumnName(i + 1);
					int t = md.getColumnType(i + 1);
					int s = md.getScale(i + 1);
					int p = md.getPrecision(i + 1);
					fld = new Field(name, t, s, p);
					fields.add(fld);
				}
				dt.fields = fields;
				while(rs.next()){
					data = new ArrayList<Object>();
					for(int i = 0; i < count; i++){
						data.add(rs.getObject(i+1));
					}
					row = new Row(fields, data);
					dt.data.add(row);
		        }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dt;
	}
	
}
