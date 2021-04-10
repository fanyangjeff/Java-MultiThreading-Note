package com.company;

import java.util.Random;

public class RunnableDemo implements Runnable {
    Thread t;
    String threadName;

    RunnableDemo(String name) {
        this.threadName = name;

        //if we do not set the target to 'this' object, then the run method of this class will not be executed
        t = new Thread(this, this.threadName);
        t.start();
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " starts running");
        Random rand = new Random();
        for(int i = 0; i < 5; i++) {
            int restTime = rand.nextInt(100) * (10 - i);
            try {
                System.out.println("Current thread: " + Thread.currentThread().getName() + "  my thread: " + threadName + " " + i);
                System.out.println(Thread.currentThread().getName() + " is resting for " + restTime + " milliseconds");
                Thread.sleep(restTime);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
