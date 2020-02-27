package com.offcn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author
 * @create 2020-02-27 11:57
 *
 * Lock锁可以实现精确唤醒
 * synchronized只能全部唤醒或随机唤醒
 */

public class SyncAndLockDemo {
    private int number = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void print(){
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try{
            //判断
            while (number != 2){
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 3;
            condition3.signal();


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

        lock.lock();
        try{
            //判断
            while (number != 3){
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 1;
            condition1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            condition2.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        public void print10(){
        lock.lock();
        try{
            //判断
            while (number != 2){
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 3;
            condition3.signal();


        }catch(Exception e){
            e.printStackTrace();
        }finally{
           lock.unlock();
        }

    }
    public void print15(){
        lock.lock();
        try{
            //判断
            while (number != 3){
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 1;
            condition1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        SyncAndLockDemo sl = new SyncAndLockDemo();
        new Thread(()->{
            for (int i = 1 ; i <=10 ; i++) {
                sl.print5();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1 ; i <=10 ; i++) {
                sl.print10();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1 ; i <=10 ; i++) {
                sl.print15();
            }
        },"CC").start();
    }
}
