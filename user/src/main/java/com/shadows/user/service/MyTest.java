package com.shadows.user.service;

class MyTest extends Thread {
    volatile boolean stop = false;
    public static void main( String args[] ) throws Exception {
        MyTest thread = new MyTest();
        System.out.println( "Starting thread..." );
        thread.start();
        Thread.sleep( 3000 );
        //System.out.println( "hread stop..." );
        // thread.stop = true;  //如果线程阻塞，将不会检查此变量
        thread.interrupt();
        Thread.sleep( 3000 );
        System.out.println( "end..." );
    }

    public void run() {
        while ( !stop ) {
            System.out.println( "Thread running..." );
            try {
                Thread.sleep( 1000 );
            } catch ( InterruptedException e ) {
                System.out.println( "Thread interrupted..." );
            }
        }
        System.out.println( "Thread exiting under request..." );

    }
}