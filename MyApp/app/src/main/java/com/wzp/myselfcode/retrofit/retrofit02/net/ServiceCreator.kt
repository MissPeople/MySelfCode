package com.wzp.myselfcode.retrofit.retrofit02

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {
    private const val BASE_URL = "https://www.mxnzp.com/api/convert/"
    val tag = "http_info"
    val retrofit = buildRetrofit(buildClient())

    private fun buildClient() : OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor{
            Log.e(tag, "buildClient: $it", )
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor { chain->
                var request = chain.request()
                val builder = request.newBuilder()
                request = builder.build()
                chain.proceed(request)
            }
        builder.addInterceptor(loggingInterceptor)
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
        return builder.build()
    }

    private fun buildRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)

    inline fun <reified T> create():T = create(T::class.java)
}