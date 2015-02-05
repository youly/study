package com.lastww.study.spring;

/**
 * Created by liuweiwei on 15-2-4.
 */

public class B {

    private C c;


    public void setC(C c) {
        System.out.println("B.setC initialed");
        this.c = c;
    }

/*
    public B(C c) {
        this.c = c;
        System.out.println("B initialed");
    }
*/

    public String funb() {
        System.out.println("funb");
        return "B.funb";
    }

    public B() {
        System.out.println("B initialed");
    }
}
