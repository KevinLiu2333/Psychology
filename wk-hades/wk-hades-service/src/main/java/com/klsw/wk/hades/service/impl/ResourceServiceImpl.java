package com.klsw.wk.hades.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.wk.hades.dao.ResourceMapper;
import com.klsw.wk.hades.domain.Resource;
import com.klsw.wk.hades.service.AbstractService;
import com.klsw.wk.hades.service.IResourceService;

@Service("resourceService")
public class ResourceServiceImpl extends AbstractService<Resource, Long> implements IResourceService {

    @Autowired
    private ResourceMapper resourceDao;
    
    @Autowired
    private IResourceService resourceService;

    //这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(resourceDao);
    }

    @Override
    public List<Resource> findResourcesByUserId(int userId) {
        return resourceDao.findResourcesByUserId(userId);
    }

    @Override
    public List<Resource> queryResourceList(Map<String, Object> parameter) {
        return resourceDao.queryResourceList(parameter);
    }

}
