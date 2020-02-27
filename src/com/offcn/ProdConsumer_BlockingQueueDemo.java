package com.offcn;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author
 * @create 2020-02-27 14:04
 */
class MySource{
    private volatile boolean FLAG = true;//默认开启生产和消费
    AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MySource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    //生产者
    public void pro() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停,表示FLAG为false,停止生产");
    }

    public void consum() throws InterruptedException {

        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equals("")){
                //FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有拿到数据,消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}

public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) {
        MySource mySource = new MySource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
                mySource.pro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
                mySource.consum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停main线程,活动结束");
        mySource.stop();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
