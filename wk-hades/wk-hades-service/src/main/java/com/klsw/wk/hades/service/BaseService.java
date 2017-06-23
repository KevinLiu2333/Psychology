package com.klsw.wk.hades.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T, ID extends Serializable> {

    int insert(T t);

    int insertBatch(List<T> t);

    int deleteBatchById(List<ID> ids);

    int deleteById(ID id);

    int deleteByUUID(String uuid);

    int update(T t);

    T find(Map<String, Object> parameter);

    T findById(ID id);

    T findByUUID(String uuid);

    T findByName(String name);

    List<T> queryListAll(Map<String, Object> parameter);

    List<T> queryListByPage(Map<String, Object> parameter);

    int count(Map<String, Object> parameter);

    /************************************************/
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
