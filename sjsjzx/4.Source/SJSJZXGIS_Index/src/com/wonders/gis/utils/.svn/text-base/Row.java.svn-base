/**
 * 
 */
package com.wonders.gis.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author XiaQiuQin
 * 行集对象
 */
/**
 * @author XiaQiuQin
 *
 */
public class Row {
	private List<Object> data = new ArrayList<Object>();//数据
	private List<Field> fields = new ArrayList<Field>();//字段
	
	/**
	 * 构造函数
	 */
	public Row(){
		
	}
	
	/** 构造函数
	 * @param fields 字段
	 * @param data 数据
	 */
	public Row(List<Field> fields, List<Object> data){
		if(fields != null){
			this.fields = fields;
		}
		if(data != null){
			this.data = data;
		}
	}
	
	/** 添加数据
	 * @param field 字段
	 * @param value 数值
	 */
	public void add(String field, Object value){
		add(new Field(field), value);
	}
	
	/** 添加数据
	 * @param field 字段
	 * @param value 数值
	 */
	public void add(Field field, Object value){
		if(validField(field.getFieldName())){
			fields.add(field);
			data.add(value);
		}
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
	
	/** 添加数据
	 * @param data 键值对
	 */
	public void add(Hashtable<String, Object> data){
		for (String key : data.keySet()){
			add(new Field(key), data.get(key));
		}
	}
	
	/** 获取索引的数据
	 * @param index 索引
	 * @return 数据
	 */
	public Object get(int index){
		if(index >= 0 && index < data.size()){
			return data.get(index);
		}
		return null;
	}
	
	/** 获取字段名称的数据
	 * @param fieldName 字段名称
	 * @return 数据
	 */
	public Object get(String fieldName){
		int index = getFieldIndex(fieldName);
		if(index >=0 && index < data.size()){
			return data.get(index);
		}
		return null;
	}
	
	/** 获取字段的索引
	 * @param fieldName 字段名称
	 * @return 字段的索引
	 */
	public int getFieldIndex(String fieldName){
		return fields.indexOf(fieldName);
	}
	
	/** 数据转换为数组
	 * @return 数组
	 */
	public Object[] toArray(){
		return data.toArray();
	}
	
	/** 获取字段数组
	 * @return 字段数组
	 */
	public Field[] getFields(){
		return (Field[])fields.toArray();
	}
	
	/** 移除索引的数据和字段
	 * @param index 索引
	 */
	public void remove(int index){
		if(index >=0 && index < data.size()){
			fields.remove(index);
			data.remove(index);
		}
	}
	
	/** 移除字段名称的字段和数据
	 * @param fieldName 字段名称
	 */
	public void remove(String fieldName){
		int index = getFieldIndex(fieldName);
		fields.remove(index);
		data.remove(index);
	}
	
	/** 数据长度
	 * @return 数据长度
	 */
	public int size(){
		return data.size();
	}
	
	/** set index的 数据
	 * @param index 索引
	 * @param value 数据
	 */
	public void set(int index, Object value){
		if(index >=0 && index < data.size()){
			data.set(index, value);
		}
	}
	
	/** set数据
	 * @param fieldName 字段名称
	 * @param value 数据
	 */
	public void set(String fieldName, Object value){
		int index = getFieldIndex(fieldName);
		if(index >=0 && index < data.size()){
			data.set(index, value);
		}
	}

}
