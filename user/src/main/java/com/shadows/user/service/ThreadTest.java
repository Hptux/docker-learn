package com.shadows.user.service;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.testThread2();
    }

    private void testStringChange() {
        String str = "121";
        String str2 = str.intern();
        String tempStr = new String("121");
        System.out.println(str == str2);
        System.out.println(str == tempStr.intern());
        System.out.println(tempStr == tempStr.intern());
        str = str + "322";
        System.out.println(str);
        System.out.println(tempStr);
        System.out.println(str == tempStr);
        System.out.println(str.equals(tempStr));
    }

    private void testThread() {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            synchronized (ThreadTest.class) {
                System.out.println("thread1 start, time:" + (System.currentTimeMillis() - start));
                try {
                    System.out.println("thread1 wait start, time:" + (System.currentTimeMillis() - start));
                    TempTest.class.wait();
                    System.out.println("thread1 wait end, time:" + (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 going on, time:" + (System.currentTimeMillis() - start));
                System.out.println("thread1 end, time:" + (System.currentTimeMillis() - start));
            }
        });


        Thread thread2 = new Thread(() -> {
            synchronized (ThreadTest.class) {
                System.out.println("thread2 start, time:" + (System.currentTimeMillis() - start));
                ThreadTest.class.notify();
                try {
                    System.out.println("thread2 sleep, time:" + (System.currentTimeMillis() - start));
                    Thread.sleep(2000L);
                    System.out.println("thread2 sleep end, time:" + (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 going on, time:" + (System.currentTimeMillis() - start));
                System.out.println("thread2 end, time:" + (System.currentTimeMillis() - start));
            }
        });

        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread sleep end thread1 status:" + thread1.getState());
        thread2.start();
    }

    private void testThread2() {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            synchronized (ThreadTest.class) {
                System.out.println("thread1 start, time:" + (System.currentTimeMillis() - start));
                try {
                    System.out.println("thread1 wait start, time:" + (System.currentTimeMillis() - start));
                    Thread.sleep(20000);
                    System.out.println("thread1 wait end, time:" + (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 going on, time:" + (System.currentTimeMillis() - start));
                System.out.println("thread1 end, time:" + (System.currentTimeMillis() - start));
            }
        });


        Thread thread2 = new Thread(() -> {
            try {
                synchronized (ThreadTest.class) {
                    System.out.println("thread2 start, time:" + (System.currentTimeMillis() - start));
                    try {
                        System.out.println("thread2 sleep, time:" + (System.currentTimeMillis() - start));
                        Thread.sleep(2000L);
                        System.out.println("thread2 sleep end, time:" + (System.currentTimeMillis() - start));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread2 going on, time:" + (System.currentTimeMillis() - start));
                    System.out.println("thread2 end, time:" + (System.currentTimeMillis() - start));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("thread2 exception, time:" + (System.currentTimeMillis() - start));
            }
        });

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread sleep end thread1 status:" + thread1.getState());
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread2 status:" + thread2.getState());
        thread2.interrupt();
        System.out.println("main thread2 status:" + thread2.getState());
        try {
            System.out.println("main thread waiting ...");
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end ...");
    }

    private void testThread3() {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            synchronized (ThreadTest.class) {
                System.out.println("thread1 start, time:" + (System.currentTimeMillis() - start));
                try {
                    System.out.println("thread1 wait start, time:" + (System.currentTimeMillis() - start));
                    Thread.sleep(20000);
                    System.out.println("thread1 wait end, time:" + (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 going on, time:" + (System.currentTimeMillis() - start));
                System.out.println("thread1 end, time:" + (System.currentTimeMillis() - start));
            }
        });


        Thread thread2 = new Thread(() -> {
            try {
                synchronized (ThreadTest.class) {
                    System.out.println("thread2 start, time:" + (System.currentTimeMillis() - start));
                    try {
                        System.out.println("thread2 sleep, time:" + (System.currentTimeMillis() - start));
                        Thread.sleep(2000L);
                        System.out.println("thread2 sleep end, time:" + (System.currentTimeMillis() - start));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread2 going on, time:" + (System.currentTimeMillis() - start));
                    System.out.println("thread2 end, time:" + (System.currentTimeMillis() - start));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("thread2 exception, time:" + (System.currentTimeMillis() - start));
            }
        });

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (thread1) {
            while (thread1.isAlive()) {
                try {
                    System.out.println("main thread waiting ...");
                    thread1.wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main thread going on");

        }

        try {
            System.out.println("main thread sleep, time:" + (System.currentTimeMillis() - start));
            Thread.sleep(2000L);
            System.out.println("main thread end, time:" + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
