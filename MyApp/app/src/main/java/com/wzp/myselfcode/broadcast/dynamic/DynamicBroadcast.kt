package com.wzp.myselfcode.broadcast.dynamic

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.BroadcastDynamicBinding

class DynamicBroadcast: AppCompatActivity() {
    private lateinit var binding: BroadcastDynamicBinding
    //private val bindings by lazy { BoradcastDynamicBinding.inflate(layoutInflater) }
    private lateinit var intentFilter:IntentFilter
    private lateinit var myBroadcastReceiver:MyBroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BroadcastDynamicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intentFilter = IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        myBroadcastReceiver = MyBroadcastReceiver()
        registerReceiver(myBroadcastReceiver,intentFilter)

        binding.button.setOnClickListener {
            val intent = Intent("android.net.conn.CONNECTIVITY_CHANGE")
            intent.putExtra("aaa","cascascas")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }
}