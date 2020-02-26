package com.offcn;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author
 * @create 2020-02-24 18:23
 */
public class T1 {
    volatile int n = 0;
    public void add() {
        n++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    t1.add();
                   t1.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t"+t1.atomicInteger);
        System.out.println(Thread.currentThread().getName()+"\t"+t1.n);


        new Thread().start();
    }
}
