package com.wzp.myselfcode.retrofit.retrofit02.net

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object NetWork {
    private const val appId = "rgihdrm0kslojqvm"
    private const val appSecret = "WnhrK251TWlUUThqaVFWbG5OeGQwdz09"
    private val apiService = ServiceCreator.create(Retrofit02ApiService::class.java)

    suspend fun lookupWord(string: String) = apiService.lookupWord(string, appId, appSecret).await()

    private suspend fun <T> Call<T>.await() :T?{
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body() as T
                    val code = response.code()
                    if(code == 200){
                        continuation.resume(body)
                    }else{
                        continuation.resume(null)
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resume(null)
                }

            })
        }
    }


}