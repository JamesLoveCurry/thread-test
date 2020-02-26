package com.offcn;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author
 * @create 2020-02-25 12:16
 */
public class ThreadDemo extends Thread {



    Set<Integer> set = new HashSet<>();

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/

        List<String> list1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list1.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list1);
            }, String.valueOf(i)).start();
        }

        Set<String> set = new CopyOnWriteArraySet<>();
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("aaa","bbb");
    }
}
