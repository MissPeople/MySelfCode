package com.wzp.myselfcode.RxJava_Observe;


public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

