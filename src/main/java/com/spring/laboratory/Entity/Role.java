package com.spring.laboratory.Entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    private Integer id;
    private String role;
    private String descripton;//用户描述，UI界面显示使用
    private Boolean available = Boolean.FALSE;//   是否可用,如果不可用将不会添加给用户
    private List<UserInfo> userInfos;
    private List<Permission> permissions;
}
