package com.wzp.myselfcode.retrofit.retrofit02

data class Retrofit02Response(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val explanation: String,
        val pinyin: String,
        val radicals: String,
        val strokes: Int,
        val traditional: String,
        val word: String
    )
}