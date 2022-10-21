package com.wzp.myselfcode.retrofit.retrofit01.bean

import com.wzp.myselfcode.retrofit.retrofit01.bean.Data

data class ResponseInfo(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
)