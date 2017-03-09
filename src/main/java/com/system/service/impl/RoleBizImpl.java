package com.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.entity.Role;
import com.google.common.collect.Sets;
import com.system.dao.RoleDao;
import com.system.service.ResourceBiz;
import com.system.service.RoleBiz;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleBizImpl implements RoleBiz {

    @Resource
    private RoleDao roleDao;
    @Resource(name = "resourceBizImpl")
    private ResourceBiz resourceBiz;

    public void createRole(Role role) {
        roleDao.createRole(role);
    }

    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = roleDao.findAll();
        for(Role role : list) {
        	if(StringUtils.isEmpty(role.getResourceIdsStr())) continue;
        	Set<String> set = resourceBiz.findPermissions(Sets.newHashSet(role.getResourceIds()));
        	role.setResourceIdsStr(StringUtils.join(set.iterator(), ","));
        }
        return list;
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceBiz.findPermissions(resourceIds);
    }
}
