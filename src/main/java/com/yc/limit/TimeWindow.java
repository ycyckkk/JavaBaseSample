package com.yc.limit;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 基于划窗的限流，时间间隔和最大请求数
 * 定义方法：清理队列的数据；查询当前队列数据；数据放入队列当中
 * 判断逻辑：1）队列的时间+时间 < 当前时间，即为失效，反之为生效状态
 * 设置ConcurrentLinkedQueue队列，peek，poll，offer操作队列
 * @Author yucheng
 * @Date 2020/12/16 11:22
 */
public class TimeWindow {

    private Integer maxCount;


    private Integer seconds;

    //定义队列
    private ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<Long>();


    public TimeWindow(Integer maxCount, Integer seconds) {
        this.maxCount = maxCount;
        this.seconds = seconds;

        //设置清理数据的线程
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        //每5秒钟去清理一次线程
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        // 6秒钟最大访问1次
        int maxCount = 1;
        final TimeWindow timeWindow = new TimeWindow(10, 6);

        for (int i = 0; i < 13; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeWindow.takeAndSet();
                }
            };
        }


    }

    public void takeAndSet() {
        int count = getValidSizeQueue();
        if (count > maxCount) {
            System.out.println("超过最大的请求");
            return;
        }
        synchronized (this) {
            if (getValidSizeQueue() > maxCount) {
                System.out.println("超过最大的请求");
                return;
            }
            long now = System.currentTimeMillis();
            queue.offer(now);
        }
        System.out.println("queue中的数量" + queue.size() + ", maxCount =" + maxCount);
    }

    public int getValidSizeQueue() {
        Iterator<Long> iterator = queue.iterator();
        int count = 0;
//        synchronized (this) {
        Long c = System.currentTimeMillis() - seconds * 1000;
        while (iterator.hasNext()) {
            long long1 = iterator.next();
            if (long1 > c) {
                count++;
            }
        }
//        }
        return count;
    }


    public void clean() {
        Long c = System.currentTimeMillis() - seconds * 1000;

        //从队列中取出过期的元素进行清理
        Long long1 = null;
        while ((long1 = queue.peek()) != null && long1 < c) {
            System.out.println("从队列中清理数据");
            queue.poll();
        }
    }

}