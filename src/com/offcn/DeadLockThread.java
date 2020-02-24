package com.offcn;

import java.util.concurrent.TimeUnit;

/**
 * @author
 * @create 2020-02-22 14:35
 */


class MyLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public MyLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有:"+lockA+"\t尝试获取:"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有:"+lockB+"\t尝试获取:"+lockA);
            }
        }

    }
}

public class DeadLockThread {
    public static void main(String[] args) {
        /*String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new MyLockThread(lockB,lockA),"ThreadBBB").start();
        long l = Runtime.getRuntime().totalMemory()/1024/1024;
        long l1 = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println(l+"\n");
        System.out.println(l1);*/

        System.out.println(sun.misc.VM.maxDirectMemory()/(double) 1024/1024+"MB");

    }
}
