package com.bobo.json.gson;

import com.bobo.json.gson.constant.RoleEnum;
import com.bobo.json.gson.constant.StatusEnum;
import com.bobo.json.gson.custom.BasicEnumTypeAdapterFactory;
import com.bobo.json.gson.entity.OrderDO;
import com.bobo.json.gson.entity.UserDO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author bobo
 * @date 2021/9/5
 */
public class EnumSerializeDeserializeTest {

    public static void main(String[] args) {
        //注册自定义的TypeAdapterFactor
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new BasicEnumTypeAdapterFactory())
                .create();

        //实体类:OrderDO{orderNo="202109050001", status=StatusEnum.PENDING} <-->  json串:{"orderNo":"202109050001","status":{"value":2,"desc":"处理中"}}
        String orderTo=gson.toJson(new OrderDO("202109050001", StatusEnum.PENDING));
        System.out.println(String.format("value为String类型,序列化结果:%s",orderTo));

        OrderDO orderFrom=gson.fromJson(orderTo,OrderDO.class);
        System.out.println(String.format("value为String类型,反序列化结果:%s",orderFrom));

        //实体类:UserDO{name='bobo', role=RoleEnum.ADMIN} <-->  json串:{"name":"bobo","role":{"value":"admin","desc":"管理员"}}
        String userTo=gson.toJson(new UserDO("bobo", RoleEnum.ADMIN));
        System.out.println(String.format("value为Integer类型,序列化结果:%s",userTo));

        UserDO userFrom=gson.fromJson(userTo,UserDO.class);
        System.out.println(String.format("value为Integer类型,反序列化结果:%s",userFrom));
    }

}
