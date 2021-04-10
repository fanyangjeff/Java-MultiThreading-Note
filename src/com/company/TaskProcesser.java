package com.company;

import java.util.LinkedList;
import java.util.Queue;

/*
    This file demonstrates how to use Object.wait() and Object.notifyAll() by processing multiple tasks with more than
    one threads
 */

public class TaskProcesser {
    private TaskQueue taskQueue;
    private LinkedList<Thread> threads;

    TaskProcesser() {
        taskQueue = new TaskQueue();
        threads = new LinkedList<Thread>();

        //we will create five threads to process tasks
        for(int i = 0; i < 5; i++) {

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
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.poll();
    }

}
