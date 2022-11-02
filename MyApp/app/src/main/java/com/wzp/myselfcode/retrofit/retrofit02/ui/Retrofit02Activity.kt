package com.wzp.myselfcode.retrofit.retrofit02.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.UseRetrofit02Binding
import com.wzp.myselfcode.retrofit.retrofit02.net.NetWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Retrofit02Activity: AppCompatActivity() {
    private lateinit var binding:UseRetrofit02Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UseRetrofit02Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tetrofit02Button.setOnClickListener {
            if(!binding.retrofit02Input.text.equals("")){
                CoroutineScope(Dispatchers.IO).launch {
                    val lookupWord =
                        NetWork.lookupWord(binding.retrofit02Input.text.trim().toString())
                    lookupWord?.apply {
                        CoroutineScope(Dispatchers.Main).launch {
                            binding.r02Word.setText(lookupWord.data[0].word)
                            binding.r02Traditional.setText(lookupWord.data[0].traditional)
                            binding.r02Pinyin.setText(lookupWord.data[0].pinyin)
                            binding.r02Radicals.setText(lookupWord.data[0].radicals)
                            binding.r02Strokes.setText(lookupWord.data[0].strokes)
                            binding.r02Explanation.setText(lookupWord.data[0].explanation)
                        }

                    }
                }
            }
        }
    }
}