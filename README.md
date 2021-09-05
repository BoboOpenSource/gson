#### Serialization and deserialization of custom enum types ,based on gson
#### 基于gson，自定义枚举类型序列化反序列化，实际效果如下：
实体类:OrderDO:{orderNo="202109050001", status=StatusEnum.PENDING} <-->  json串:{"orderNo":"202109050001","status":{"value":2,"desc":"处理中"}}

#### 需求背景：将枚举元素序列化为带有值和说明(value/desc)的json串，反序列化则基于值(value)进行匹配

#### 问题：由于gson的JsonSerializer、JsonDeserializer只支持单个具体类型，如果有多个enum类型，就得实现多个JsonSerializer、JsonDeserializer，并注册多次，只能寻找其他解决方式

#### 解决方式：gson的TypeAdapterFactory支持根据对象类型动态返回一个TypeAdapter，执行序列化与反序列化，正好适用<br>
  1、自定义BasicEnumTypeAdapterFactory(实现TypeAdapterFactory#create方法)<br>
    逻辑：如果枚举类实现了BasicEnum，使用BasicEnumTypeAdapter执行序列化、反序列化<br>
  2、自定义BasicEnumTypeAdapter(实现TypeAdapter#write,TypeAdapter#read)<br>
    逻辑：将所有实现了BasicEnum的枚举类(如StatusEnum)序列化为类似于{"value":"1","desc":"初始化"}这种包含value、desc的json串)<br>
  3、使用GsonBuilder#registerTypeAdapterFactory注册BasicEnumTypeAdapterFactory
 
 #### 测试类：EnumSerializeDeserializeTest
