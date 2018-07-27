package com.wangzai.sort;

public class Test {

    public synchronized static void read() throws InterruptedException {
        System.out.println("开始读取");
        Thread.sleep(5000);
        System.out.println("读取完毕");
    }

    public synchronized static void write() throws InterruptedException {
        System.out.println("开始写入");
        Thread.sleep(5000);
        System.out.println("写入完毕");
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Test.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Test.write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
