package com.wzp.myselfcode.retrofit.retrofit01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wzp.myselfcode.databinding.UseRetrofitBinding
import com.wzp.myselfcode.retrofit.retrofit01.bean.ResponseInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity: AppCompatActivity() {

    private lateinit var binding:UseRetrofitBinding
    private val appId = "rgihdrm0kslojqvm"
    private val appSecret = "WnhrK251TWlUUThqaVFWbG5OeGQwdz09"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UseRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestInfo()
    }

    private fun requestInfo() {
        //4,创建retrofit对象
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.mxnzp.com/api/convert/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //5，创建网络请求接口的实例
        val request = retrofit.create(ApiRequest::class.java)
        //对发送请求进行封装
        val call = request.getCall("煮",appId,appSecret)
        //6发送网络请求
        call.enqueue(object :Callback<ResponseInfo>{
            override fun onResponse(call: Call<ResponseInfo>, response: Response<ResponseInfo>) {
                response.body()?.apply {
                    val code1 = this.code
                    val msg1 = this.msg
                    val data1 = this.data
                    Log.e("TAG", "onResponse: $code1", )
                    Log.e("TAG", "onResponse: $msg1", )
                    Log.e("TAG", "onResponse: $data1", )
                }

            }

            override fun onFailure(call: Call<ResponseInfo>, t: Throwable) {
                println("连接失败")
            }

        })

    }

}