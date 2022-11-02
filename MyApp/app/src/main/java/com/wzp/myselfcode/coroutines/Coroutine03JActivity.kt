package com.wzp.myselfcode.coroutines

import kotlinx.coroutines.*


//---------------------------TEST01
/*suspend fun main() {
    coroutineScope {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }
}*/

//---------------------------TEST02
/*fun main() {
    runBlocking {
        launch(Dispatchers.Unconfined) {
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500L)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }
}*/

//---------------------------TEST03
/*
fun log(msg:String) = println("[${Thread.currentThread().name}] $msg")
fun main() {
    newSingleThreadContext("Ctx1").use { ctx1->
        newSingleThreadContext("Ctx2").use { ctx2->
            runBlocking(ctx1){
                log("start ctx1")
                withContext(ctx2){
                    log("start ctx2")
                }
                log("back to ctx1")
            }
        }
    }
}*/

//---------------------------TEST04
fun main() {
    runBlocking {
        val request = launch {
            launch(Job()){
                println("job1: I run in my own Job and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request")
            }
        }
        delay(500)
        request.cancel() // cancel processing of the request
        println("main: Who has survived request cancellation?")
        delay(1000) // delay the main thread for a second to see what happens
    }
}
