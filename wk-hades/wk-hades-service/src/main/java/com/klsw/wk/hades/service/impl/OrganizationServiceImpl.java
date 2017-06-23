package com.klsw.wk.hades.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.klsw.wk.hades.dao.OrganizationMapper;
import com.klsw.wk.hades.domain.Organization;
import com.klsw.wk.hades.service.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
	@Autowired
    private OrganizationMapper organizationMapper;

	@Override
    public List<Organization> getAll(Organization organization) {
		if (organization.getPage() != null && organization.getRows() != null) {
            PageHelper.startPage(organization.getPage(), organization.getRows());
        }
        return organizationMapper.selectAll();
    }

	@Override
    public Organization getById(Integer id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

	@Override
    public void deleteById(Integer id) {
    	organizationMapper.deleteByPrimaryKey(id);
    }

	@Override
    public void save(Organization organization) {
        if (organization.getId() != null) {
            organizationMapper.updateByPrimaryKey(organization);
        } else {
        	organizationMapper.insert(organization);
        }
    }
}
