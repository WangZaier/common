package com.wangzai.map;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        WZMap<String, String> map = new WZHashMap();


        for (int i = 0; i < 100; i++) {
            map.put("" + i, "" + i);
        }

        System.out.println(map.size());
        for (int i = 0; i < 100; i++) {
            System.out.println(map.get("" + i));
        }


    }
}
