package com.offcn;

import sun.nio.ch.DefaultSelectorProvider;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author
 * @create 2020-02-25 11:34
 */
public class ABADemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 101, 1, 2));
            System.out.println(atomicStampedReference.compareAndSet(101, 100, 2, 3));
            System.out.println(Thread.currentThread().getName()+"\t当前值为:"+atomicStampedReference.getReference()+"\t当前版本号是:"+atomicStampedReference.getStamp());
        },"t1").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 2019, 1, 2));
            System.out.println(Thread.currentThread().getName()+"\t当前值为:"+atomicStampedReference.getReference()+"\t当前版本号是:"+atomicStampedReference.getStamp());
        },"t2").start();




    }
}
