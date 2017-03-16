package com.system.dao;


import java.util.List;

import com.entity.Resource;

public interface ResourceDao {

    public void createResource(Resource resource);

    public void updateResource(Resource resource);

    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);

    List<Resource> findAll();
}
