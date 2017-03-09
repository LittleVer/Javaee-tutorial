package com.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.dao.StaffDao;
import com.entity.Role;
import com.entity.Staff;
import com.entity.User;
import com.entity.custom.UserVo;
import com.system.dao.RoleDao;
import com.system.dao.UserDao;
import com.system.service.RoleBiz;
import com.system.service.UserBiz;
import com.util.PasswordHelper;

/**
 * Created by c0de8ug on 16-2-9.
 */

@Service
public class UserBizImpl implements UserBiz {

	@Autowired
    UserDao userDao;

	@Autowired
    RoleDao roleDao;

	@Autowired
    StaffDao staffDao;

	@Autowired
    private PasswordHelper passwordHelper;
	@Autowired
    private RoleBiz roleBiz;

    @Override
    public List<UserVo> findAll() throws InvocationTargetException, IllegalAccessException {
        List<UserVo> userVoList = new ArrayList<>();
        List<User> userList = userDao.findAll();


        Iterator<User> iterator = userList.iterator();

        while (iterator.hasNext()) {
            StringBuilder s = new StringBuilder();
            User user = (User) iterator.next();
            List<Long> roleIds = user.getRoleIds();

            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userVo, user);

            if (roleIds != null) {
                int i = 0;
                int size = roleIds.size();
                for (; i < size - 1; i++) {
                    Role role = roleDao.findOne(roleIds.get(i));

                    s.append(role.getDescription());
                    s.append(",");
                }
                Role role = roleDao.findOne(roleIds.get(i));
                s.append(role.getDescription());
                userVo.setRoleIdsStr(s.toString());
            }


            userVoList.add(userVo);

        }

        return userVoList;
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {
    	if(user.getPassword()!=null) {
    		String newPw = passwordHelper.encryptPassword(user.getPassword(), user.getSalt());
    		user.setPassword(newPw);
    	}
        userDao.update(user);
    }

    public void add(User user) {
        passwordHelper.encryptPassword(user);
        userDao.add(user);
        String id = user.getUserId();
        String teacherRoleId = roleDao.findByDescription("老师").getId().toString();
        if (user.getRoleIdsStr().indexOf(teacherRoleId) != -1) {
            Staff staff = new Staff();
            staff.setStaffId(id);
            staff.setStaffName(id);
            staffDao.add(staff);
        }


    }

    //TODO 删除staff和student连带的功能未完成
    @Transactional
    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void changePassword(String userId, String newPassword) {
        User user = userDao.findById(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.update(user);

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findById(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return Collections.emptySet();
        }
        return roleBiz.findRoles(user.getRoleIds().toArray(new Long[0]));
    }

    @Override
    public Set<String> findPermissions(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return Collections.emptySet();
        }
        return roleBiz.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }
}

