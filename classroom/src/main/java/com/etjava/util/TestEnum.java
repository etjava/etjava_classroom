package com.etjava.util;

import java.util.HashMap;
import java.util.Map;

public class TestEnum {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        for (SystemFilePath f:SystemFilePath.values()){
            map.put(f.name(),f.getUrl());
        }

        System.out.println(map.get("AUTO_COURSE_TARGET"));



        String s = "http://localhost/course/list/2&s_type=vip&ss_typeId=2";
        System.out.println(s.replace("&","?"));
    }
}
