package com.company;

public class DaemonThread extends Thread{
    private Thread t;

    DaemonThread() {
        t = new Thread(this,"daemonThread");

        //set Daemon to true, otherwise the JVM progress will never stop
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void run() {
        while(true) {
            try{
                System.out.println("thread Running");
                Thread.sleep(1000);
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
