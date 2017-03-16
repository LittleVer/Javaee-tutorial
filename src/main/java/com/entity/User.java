package com.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

public class User {
    String username; //用户名
    @NotBlank
    String userId;
    String password;
    String[] passwords;
    String salt;
    private List<Long> roleIds; //拥有的角色列表
    private String roleIdsStr;
    private String roleDesc;
    private Boolean locked = Boolean.FALSE;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public void setRoleIds(List<Long> roleIds) {
    	this.roleIds = roleIds;
    	StringBuilder s = new StringBuilder();
        int i = 0;
        for (; i < roleIds.size() - 1; i++) {
            Long roleId = roleIds.get(i);
            s.append(roleId);
            s.append(",");
        }
        s.append(roleIds.get(i));

        this.roleIdsStr = s.toString();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return userId + salt;
    }

	public String[] getPasswords() {
		return passwords;
	}

	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}

	public String getRoleIdsStr() {
        return roleIdsStr;
    }

    public void setRoleIdsStr(String roleIdsStr) {
    	this.roleIdsStr = roleIdsStr;
        if (StringUtils.isEmpty(roleIdsStr)) {
            return;
        }
        roleIds = new ArrayList<>();
        String[] roleIdStrs = roleIdsStr.split(",");
        for (String roleIdStr : roleIdStrs) {
            if (StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            roleIds.add(Long.valueOf(roleIdStr));
        }
    }

	@Override
	public String toString() {
		return "User [username=" + username + ", userId=" + userId
				+ ", password=" + password + ", passwords="
				+ Arrays.toString(passwords) + ", salt=" + salt + ", roleIds="
				+ roleIds + ", roleIdsStr=" + roleIdsStr + ", roleDesc="
				+ roleDesc + ", locked=" + locked + "]";
	}
}
