package com.wzp.myselfcode.RxJava_Observe;

import android.util.Log;

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        subject.getState();
        Log.e("TAG", "update: ${s}" );

    }
}

