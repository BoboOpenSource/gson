package com.bobo.json.gson.entity;

import com.bobo.json.gson.constant.StatusEnum;

/**
 * @author bobo
 * @date 2021/9/5
 */
public class OrderDO {
    private String orderNo;
    private StatusEnum status;

    public OrderDO() {
    }

    public OrderDO(String orderNo, StatusEnum status) {
        this.orderNo = orderNo;
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public StatusEnum getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "orderNo='" + orderNo + '\'' +
                ", status=" + status +
                '}';
    }
}
