package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/*
    This file demonstrates how to use Object.wait() and Object.notifyAll() by processing multiple tasks with more than
    one threads
 */

public class TaskProcesser {
    private TaskQueue taskQueue;
    private LinkedList<Thread> threads;

    TaskProcesser() throws InterruptedException {
        taskQueue = new TaskQueue();
        threads = new LinkedList<Thread>();

        //we will create five threads to process tasks
        for(int i = 0; i < 5; i++) {
            Thread newThread = new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    while(true) {
                        try {
                            String task = taskQueue.getNextTask();
                            System.out.println(Thread.currentThread().getName() + " is processing task " + task);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            threads.add(newThread);
        }

        //kick off all the threads
        for(Thread taskThread: threads) {
            taskThread.start();
        }

        Random rand = new Random();
        Thread assignTask = new Thread("assignTask") {
            @Override
            public void run() {
                for(int i = 0; i < 15; i++) {
                    int taskId = rand.nextInt(200);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is assigning task " + taskId);
                    taskQueue.addTask(String.valueOf(taskId));
                }
            }
        };

        //kick off assignTask thread
        assignTask.start();
        assignTask.join();

        System.out.println("finished assigning task");

        for(Thread taskThread: threads) {
            taskThread.interrupt();
        }


    }

}


class TaskQueue {
    Queue<String> queue = new LinkedList<String>();
    public synchronized void addTask (String newTask) {
        queue.add(newTask);
        this.notifyAll();
    }

    public synchronized String getNextTask() throws InterruptedException {

        //we must use a while loop here, because once all the threads are notified, they will compete for the resources
        //only the one of the threads can get the element in the queue, then it evaluates to false and return queue.poll()
        //The rest threads will evaluate queue.isEmpty() to true, and they will wait for future notifications.
        while (queue.isEmpty()) {

            this.wait();    //blocked until notified
            //immediately executed once notified
        }
        return queue.poll();
    }

}
