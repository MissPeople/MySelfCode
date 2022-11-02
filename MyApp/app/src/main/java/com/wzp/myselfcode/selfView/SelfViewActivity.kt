package com.wzp.myselfcode.selfView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.ActivitySelfViewBinding

class SelfViewActivity: AppCompatActivity() {
    private lateinit var binding:ActivitySelfViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}