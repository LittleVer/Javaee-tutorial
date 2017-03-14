package com.system.service;


import com.entity.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceBiz {


    public void createResource(Resource resource);
    public void updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);
	Set<String> findPermissionNames(Set<Long> resourceIds);
}
