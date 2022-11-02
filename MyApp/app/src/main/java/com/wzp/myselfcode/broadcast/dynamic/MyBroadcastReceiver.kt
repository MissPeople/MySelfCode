package com.wzp.myselfcode.broadcast.dynamic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "received in MyBroadcastReceiver:->${intent.getStringExtra("aaa")}", Toast.LENGTH_SHORT).show()
        //abortBroadcast()
    }
}