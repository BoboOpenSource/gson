package com.bobo.json.gson.custom;

import com.bobo.json.gson.constant.BasicEnum;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import  com.bobo.json.gson.constant.StatusEnum;
/**
 * @author bobo
 * @date 2021/9/4
 * @desc 将实现了 {@link BasicEnum}的枚举类(如{@link StatusEnum})序列化为类似于{"value":"1","desc":"初始化"}这种包含value、desc的json串
 * @desc 反序列化时，根据json串中的value值匹配对应枚举元素
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class BasicEnumTypeAdapter<T extends BasicEnum> extends TypeAdapter<T> {

    /**
     * 枚举类的所有枚举元素
     */
    private final T[] enumConstants;

    /**
    * 枚举类的value属性类型(当前只支持String、Integer两种value，新增类型需要修改 {@link EnumValueType}、read、write方法)
    */
    private final EnumValueType enumValueType;

    /**
    * @param type 表示当前待序列化/反序列化的对象实际类型
    */
    public BasicEnumTypeAdapter(TypeToken<T> type) {
        final Class<T> actualType = (Class) type.getRawType();
        Type[] genericInterfaceTypes = (Type[]) actualType.getGenericInterfaces();
        Class valueClazz = null;
        for (Type genericInterfaceType : genericInterfaceTypes) {
            if (genericInterfaceType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterfaceType;
                Class clazz = (Class) parameterizedType.getRawType();
                if (clazz == BasicEnum.class) {
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    valueClazz = (Class) actualTypeArguments[0];
                }
            }
        }
        if (valueClazz == null) {
            throw new IllegalArgumentException("未获取到枚举value类型");
        }
        if (valueClazz == String.class) {
            enumValueType = EnumValueType.STRING;
        } else if (valueClazz == Integer.class) {
            enumValueType = EnumValueType.INTEGER;
        } else {
            throw new IllegalArgumentException("不支持的枚举value类型");
        }
        enumConstants = actualType.getEnumConstants();
    }

    @Override
    public void write(JsonWriter out, BasicEnum value) throws IOException {
        switch (enumValueType) {
            case STRING:
                out.beginObject()
                        .name("value").value((String) value.getValue())
                        .name("desc").value(value.getDesc())
                        .endObject();
                break;
            case INTEGER:
                out.beginObject()
                        .name("value").value((Integer) value.getValue())
                        .name("desc").value(value.getDesc())
                        .endObject();
                break;
            default:
                throw new IllegalArgumentException(String.format("不支持的枚举value类型:%s", enumValueType));
        }
    }

    @Override
    public T read(JsonReader in) throws IOException {
        JsonElement jsonElement = Streams.parse(in);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Object value = null;

        switch (enumValueType) {
            case STRING:
                value = jsonObject.get("value").getAsString();
                break;
            case INTEGER:
                value = jsonObject.get("value").getAsInt();
                break;
            default:
                throw new IllegalArgumentException(String.format("不支持的枚举value类型:%s", enumValueType));
        }

        for (T basicEnum : enumConstants) {
            if (basicEnum.getValue().equals(value)) {
                return basicEnum;
            }
        }
        return null;
    }

    private enum EnumValueType {
        STRING, INTEGER
    }
}
