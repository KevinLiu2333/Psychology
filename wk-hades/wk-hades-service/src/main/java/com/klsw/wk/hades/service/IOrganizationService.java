package com.klsw.wk.hades.service;

import java.util.List;

import com.klsw.wk.hades.domain.Organization;

/**
 * 机构管理接口
 * @author liulixi
 * @version 2017年6月21日11:00:18
 */
public interface IOrganizationService {

    public List<Organization> getAll(Organization organization);

    public Organization getById(Integer id);

    public void deleteById(Integer id);

    public void save(Organization organization);
}
