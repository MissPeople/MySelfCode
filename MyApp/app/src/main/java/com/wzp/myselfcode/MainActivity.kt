package com.wzp.myselfcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wzp.myselfcode.RxJava01.RxJava01Activity
import com.wzp.myselfcode.RxJava_Observe.RxActivity
import com.wzp.myselfcode.broadcast.BroadcastActivity
import com.wzp.myselfcode.coroutines.Coroutine01Activity
import com.wzp.myselfcode.databinding.ActivityMainBinding
import com.wzp.myselfcode.dialog.UseDialogActivity
import com.wzp.myselfcode.retrofit.retrofit01.RetrofitActivity
import com.wzp.myselfcode.retrofit.retrofit02.ui.Retrofit02Activity
import com.wzp.myselfcode.selfView.SelfViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.toTestDialog.setOnClickListener {
            startActivity(Intent(this,UseDialogActivity::class.java))
        }
        binding.toTestRxJava01.setOnClickListener {
            startActivity(Intent(this,RxJava01Activity::class.java))
        }
        binding.toRetrofitActivity.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
        binding.toRxActivity.setOnClickListener {
            startActivity(Intent(this, RxActivity::class.java))
        }
        binding.toRetrofit02Activity.setOnClickListener {
            startActivity(Intent(this, Retrofit02Activity::class.java))
        }
        binding.toCoroutine.setOnClickListener {
            startActivity(Intent(this, Coroutine01Activity::class.java))
        }
        binding.toSelfView.setOnClickListener {
            startActivity(Intent(this, SelfViewActivity::class.java))
        }
        binding.toBroadcast.setOnClickListener {
            startActivity(Intent(this, BroadcastActivity::class.java))
        }
    }
}
