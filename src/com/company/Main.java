package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        RunnableDemo t1 = new RunnableDemo("threadDemo1");
//        RunnableDemo t2 = new RunnableDemo("threadDemo2");

//        Thread daemonThread = new DaemonThread();

        Thread addThread = new CounterThread();

        addThread.start();

        addThread.join();

        System.out.println(Counter.counter);
    }
}

class Counter {
    public static int counter = 0;
}

class Lock {
    public static final Object lock = new Object();
}

class CounterThread extends Thread {
    public void run() {
        for(int i = 0; i < 10000; i++) inc ();
        for(int i = 0; i < 10000; i++) dec();
    }
    public void inc () {
        Counter.counter ++;
    }

    public void dec () {
        Counter.counter --;
    }

}

