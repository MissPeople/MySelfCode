package com.wzp.myselfcode.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


suspend fun doSomethingOne():Int{
    return coroutineScope {
        delay(1000L)
        return@coroutineScope 15
    }
}
suspend fun doSomethingTwo():Int{
    return coroutineScope {
        delay(1000L)
        return@coroutineScope 30
    }
}
//----------------------TEST01
/*
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingOne()
            val two = doSomethingTwo()
            println("the answer is ${one+two}")
        }
        println("spend all time is $time")
    }
}*/

//----------------------TEST02
/*
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingOne() }
            val two = async { doSomethingTwo() }
            println("the answer is ${one.await()+two.await()}")
        }
        println("spend all time is $time")
    }

}*/

//----------------------TEST03
/*fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY){ doSomethingOne() }
            val two = async(start = CoroutineStart.LAZY){ doSomethingTwo() }
            one.start()
            two.start()
            println("the answer is ${one.await()+two.await()}")
        }
        println("spend all time is $time")
    }
}*/

//----------------------TEST04
@OptIn(DelicateCoroutinesApi::class)
fun somethingOneAsync() = GlobalScope.async {
    doSomethingOne()
}
@OptIn(DelicateCoroutinesApi::class)
fun somethingTwoAsync() = GlobalScope.async {
    doSomethingTwo()
}
/*fun main() {
    val time = measureTimeMillis {
        val one = somethingOneAsync()
        val two = somethingTwoAsync()
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}*/

//----------------------TEST05
/*suspend fun concurrentSum() :Int = coroutineScope {
    val one = async { doSomethingOne() }
    val two = async { doSomethingTwo() }
    one.await()+two.await()
}
suspend fun main() {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}*/

//----------------------TEST06
fun main() {
    runBlocking<Unit> {
        try {
            failedConcurrentSum()
        }catch (e:Exception){
            println("error finish")
        }
    }
}

suspend fun failedConcurrentSum() :Int{
    return coroutineScope {
        val one = async<Int> {
            try {
                delay(Long.MAX_VALUE)
                42
            }finally {
                println("First child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw Exception()
        }
        one.await() + two.await()
    }
}
