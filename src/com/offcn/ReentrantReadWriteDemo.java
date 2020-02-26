package com.offcn;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author
 * @create 2020-02-26 17:12
 */
public class ReentrantReadWriteDemo {


    private volatile Map<String,Object> map = new HashMap<>();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);
            try{
                TimeUnit.MICROSECONDS.sleep(300);
            }catch(InterruptedException e){
                e.printStackTrace();
            };
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
           readWriteLock.writeLock().unlock(); 
        }

    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在读取"+key);
            try{
                TimeUnit.MICROSECONDS.sleep(300);
            }catch(InterruptedException e){
                e.printStackTrace();
            };
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteDemo reentrantReadWriteDemo = new ReentrantReadWriteDemo();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new  Thread(()->{
                reentrantReadWriteDemo.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new  Thread(()->{
                reentrantReadWriteDemo.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
