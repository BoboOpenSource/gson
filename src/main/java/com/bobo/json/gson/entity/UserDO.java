package com.bobo.json.gson.entity;

import com.bobo.json.gson.constant.RoleEnum;

/**
 * @author bobo
 * @date 2021/9/5
 */
public class UserDO {
    private String name;
    private RoleEnum role;

    public UserDO(String name, RoleEnum role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    public UserDO() {
    }
}
