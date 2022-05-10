package com.example.myapp.MyJacksonSerialization;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1. Jackson只会serialize可以被get到field。如果field “name” 没有@Getter，则会忽略它
 * 2. @JsonAnyGetter allows for the flexibility of using a Map as standard properties.
 * 3. @JsonPropertyOrder : specify the order of properties on serialization
 */

@AllArgsConstructor
@JsonPropertyOrder({ "test", "name" })// specify the order of properties on serialization
public class BeanWithAnnotation {
    @Getter
    private String name;
    private Map<String, String> properties;

    @JsonRawValue //代表这个字段本身是一个json格式： instruct Jackson to serialize a property exactly as is
    private String json;

    private String test;
    @JsonGetter("test") // 为get到的field添加json里的key，不添加的话就会按照method命名的方式来
    public String getTheTest() {
        return test;
    }

    @JsonAnyGetter // JsonAnyGetter allows for the flexibility of using a Map as standard properties.
    public Map<String, String> getProperties() {
        return properties;
    }

    public static void main( String[] args ) throws JsonProcessingException {
        String json = "{\"attr\":false}";
        BeanWithAnnotation beanWithAnnotation = new BeanWithAnnotation("i am a bean", ImmutableMap.of("attr1", "value1", "attr2", "value2"), "my test");
        String result = new ObjectMapper().writeValueAsString(beanWithAnnotation);
        System.out.println(result);
    }
}
