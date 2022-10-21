package com.wzp.myselfcode.RxJava01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.UseRxjava01Binding

class RxJava01Activity: AppCompatActivity() {

    private lateinit var binding: UseRxjava01Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UseRxjava01Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}