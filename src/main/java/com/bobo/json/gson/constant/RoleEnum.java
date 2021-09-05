package com.bobo.json.gson.constant;

/**
 * @author bobo
 * @date 2021/9/3
 * @desc
 */
public enum RoleEnum implements BasicEnum<String> {
    ADMIN("admin","管理员"),
    HANDLER("handler","操作员"),
    REVIEWER("reviewer","复核员")
    ;
    private String value;
    private String desc;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    RoleEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
