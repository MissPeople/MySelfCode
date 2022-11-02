package com.wzp.myselfcode.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.UseCoroutineBinding

class Coroutine01Activity : AppCompatActivity() {
    private lateinit var binding:UseCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UseCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myCode()
    }

    private fun myCode() {

    }
}