package com.klsw.wk.hades.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 汇总比较共性的操作方法
 * @param <T> model
 * @param <ID> id
 */
public interface BaseDao<T,ID extends Serializable> {
    /**
     * 新增操作
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 批量插入操作
     * @param t
     * @return
     */
    int insertBatch(@Param("list") List<T> t);

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    int deleteBatchById(List<ID> ids);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(@Param("id") ID id);

    int deleteByUUID(String uuid);

    int update(T t);
 
    T find(Map<String, Object> parameter);
 
    T findById(@Param("id") ID id);
 
    T findByUUID(@Param("uuid") String uuid);
 
    T findByName(@Param("name") String name);

    List<T> queryListAll(Map<String, Object> parameter);
    
    List<T> queryListByPage(Map<String, Object> parameter);
    
    int count(Map<String, Object> parameter);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPK(ID id);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T queryByPK(ID id);

}
