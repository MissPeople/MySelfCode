package com.wzp.myselfcode.broadcast

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.broadcast.dynamic.DynamicBroadcast
import com.wzp.myselfcode.broadcast.standard.StandardBroadcast
import com.wzp.myselfcode.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBroadcastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toDynamic.setOnClickListener{
            startActivity(Intent(this,DynamicBroadcast::class.java))
        }
        binding.toStandard.setOnClickListener{
            startActivity(Intent(this,StandardBroadcast::class.java))
        }
    }
}