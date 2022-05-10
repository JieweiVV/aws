package com.example.myapp.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StreamTest {
    public static void main( String[] args) {
        List<FileMeta> myList = new ArrayList<>();
        myList.add(new FileMeta("file1"));
        myList.add(new FileMeta("file2"));

        List<FileMeta> result = StreamSupport.stream(myList.spliterator(), false).map(e -> setName(e)).collect(Collectors.toList());
        System.out.println(result);
    }

    public static FileMeta setName(FileMeta name) {
        name.setName(name.getName() + "wjw");
        return name;
    }
    
}
