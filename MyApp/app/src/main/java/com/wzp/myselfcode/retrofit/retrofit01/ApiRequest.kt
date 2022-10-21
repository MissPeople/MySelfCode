package com.wzp.myselfcode.retrofit.retrofit01

import com.wzp.myselfcode.retrofit.retrofit01.bean.ResponseInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("dictionary")
    fun getCall(
        @Query("content") content:String,
        @Query("app_id") appId:String,
        @Query("app_secret") appSecret:String
    ) :Call<ResponseInfo>
}