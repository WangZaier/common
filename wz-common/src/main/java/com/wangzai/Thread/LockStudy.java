package com.wangzai.Thread;

//静态.类锁
public class LockStudy {

    public synchronized static void write() {
        System.out.println("开始读");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("读完毕");
    }

    public synchronized static void read() {
        System.out.println("开始写");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("写完毕");
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LockStudy.read();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                LockStudy.write();
            }
        }).start();

    }
}
