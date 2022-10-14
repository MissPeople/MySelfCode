package com.wzp.myselfcode

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wzp.myselfcode.dialog.CustomDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.testSSS).setOnClickListener{
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