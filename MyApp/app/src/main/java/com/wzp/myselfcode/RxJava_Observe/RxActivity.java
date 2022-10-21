package com.wzp.myselfcode.RxJava_Observe;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wzp.myselfcode.R;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_activity);

        Subject subject = new Subject();
        new OctalObserver(subject);
        new BinaryObserver(subject);
        for (int i = 0; i < 100; i++) {
            if(i==20){
                subject.setState(20);
                Log.e("TAG", "onCreate:数据为20 ");
            }
            if(i==70){
                subject.setState(70);
                Log.e("TAG", "onCreate:数据为70 ");
            }
        }
    }

}
