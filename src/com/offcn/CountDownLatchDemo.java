package com.offcn;

import java.util.concurrent.CountDownLatch;

/**
 * @author
 * @create 2020-02-26 20:10
 */
public class CountDownLatchDemo {

    private static int num=6;


    public static void main(String[] args) {

        CountDownLatch count = new CountDownLatch(num);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 被灭");
                count.countDown();
            },CountryEunm.forEach_CountryEunm(i).getRetMessage()).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t 秦国统一六国");
    }
}
