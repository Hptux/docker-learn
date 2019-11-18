package com.shadows.user.service;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HeapCheck  {
    private String id;
    public HeapCheck(String id) {
        this.id = id;
    }

    public HeapCheck() {
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public static void main(String args[] ) {
        try {
            testOutOfMemoryConstPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testOutOfMemoryHeap() {
        Map<HeapCheck, HeapCheck> tempMap = new HashMap<>();

        int j = 0;
        while (true) {
            for (int i = 0; i < 100000; i++) {
                HeapCheck hp = new HeapCheck(String.valueOf(i));
                tempMap.put(hp, hp);
            }
            j ++;
            System.out.println(j);
        }
    }

    private static void testOutOfMemoryConstPool() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    private static void testOutOfMemoryMetaspace() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HeapCheck.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, objects));

            enhancer.create();
        }
    }

    private static void testStackOverflowError(int i) {
        i ++;
        if (i > 0) {
            testStackOverflowError(i);
        }
    }

}