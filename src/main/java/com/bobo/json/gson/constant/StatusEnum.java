package com.bobo.json.gson.constant;

/**
 * @author bobo
 * @date 2021/9/3
 * @desc
 */
public enum StatusEnum implements BasicEnum<Integer> {
    INIT(1,"初始化"),
    PENDING(2,"处理中"),
    FINISHED(3,"完成")
    ;
    private Integer value;
    private String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
