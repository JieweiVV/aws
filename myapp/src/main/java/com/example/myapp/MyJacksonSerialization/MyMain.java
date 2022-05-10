package com.example.myapp.MyJacksonSerialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyMain {
    public static void main( String[] args ) throws JsonProcessingException {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        String serialized = new ObjectMapper().writeValueAsString(myItem);
        System.out.println(serialized);// Default Serialization
    }
}
