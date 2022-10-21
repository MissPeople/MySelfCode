package com.wzp.myselfcode.dialog

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.UseDialogBinding

class UseDialogActivity: AppCompatActivity() {
    private lateinit var binding: UseDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UseDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Text.setOnClickListener {
            CustomDialog.Builder(this)
                .setMessage("hello")
                .setNegativeButton("取消") { p0, _ ->
                    p0.dismiss()
                }
                .setMessageColor(Color.BLACK)
                .setPositiveButton("打开浏览器") { p0, _ ->
                    val uri = Uri.parse("https://www.google.com")
                    startActivity(Intent(Intent.ACTION_VIEW,uri))
                    p0.dismiss()
                }
                //.setWith(0.8f)
                .create()
                .show()
        }
    }
}