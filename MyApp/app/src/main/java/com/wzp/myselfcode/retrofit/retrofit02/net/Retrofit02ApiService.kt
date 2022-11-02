package com.wzp.myselfcode.retrofit.retrofit02.net

import com.wzp.myselfcode.retrofit.retrofit01.bean.ResponseInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Retrofit02ApiService {
    @GET("dictionary")
    fun lookupWord(
        @Query("content") content:String,
        @Query("app_id") appId:String,
        @Query("app_secret") appSecret:String
    ) : Call<Retrofit02Response>
}