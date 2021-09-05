package com.bobo.json.gson.custom;

import com.bobo.json.gson.constant.BasicEnum;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
* @desc 如果枚举类实现了 {@link BasicEnum}，使用 {@link BasicEnumTypeAdapter}执行序列化、反序列化
*/
@SuppressWarnings({"rawtypes", "unchecked"})
public class BasicEnumTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) {
            //没有指定的转换器的时候，返回NULL，GSON会自己适配相关的解析器
            return null;
        } else {
            final Class<?>[] interfaces = rawType.getInterfaces();
            for (Class c : interfaces) {
                if (c.equals(BasicEnum.class)) {
                    //type表示当前待序列化/反序列化的对象实际类型
                    return new BasicEnumTypeAdapter(type);
                }
            }
            return null;
        }
    }
}
