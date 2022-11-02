package com.wzp.myselfcode.broadcast.standard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.BroadcastStandardBinding

class StandardBroadcast :AppCompatActivity() {
    private lateinit var binding:BroadcastStandardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BroadcastStandardBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}