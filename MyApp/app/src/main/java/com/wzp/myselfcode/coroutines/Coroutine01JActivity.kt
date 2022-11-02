package com.wzp.myselfcode.coroutines

import kotlinx.coroutines.*


//-------------------- TEST01
/*
fun main() = runBlocking {
    launch {
        delay(1000L)
        println("world")
    }
    launch {
        delay(2000L)
        println("!")
    }
    println("hello")
}*/

//--------------------TEST02
/*fun main() = runBlocking {
    launch { printSomething() }
    println("hello")
}

suspend fun printSomething() {
    delay(1000L)
    println("world!")
}*/

// --------------------TEST03
/*fun main() {
    println("start:")
    runBlocking {
        launch {
            delay(10000L)
            println("world")
        }
        println("hello")
    }
    println("!")
    println("end")
    *//*
    runBlocking 会阻塞当前线程
     *//*
}*/

//--------------------TEST04
/*fun main() {
    runBlocking {
        printStr()
    }
    println("start:")

    print("!")
}

suspend fun printStr() = coroutineScope{
    launch {
        delay(1000L)
        println("world")
    }
    println("hello")
}*/

//--------------------TEST05
/*
fun main() = runBlocking {
    printStr()
    println("!!!")
}

suspend fun printStr() = coroutineScope {
    launch {
        delay(2000L)
        println("world2")
    }
    launch {
        delay(1000L)
        println("world1")
    }
    println("hello")
}
*/
//--------------------TEST06
/*fun main() = runBlocking {
    val job = launch {
        delay(1000L)
        println("world!")
    }
    println("Hello")
    job.join()
    println("Done")
}*/
//--------------------TEST07
/*
fun main() = runBlocking {
    repeat(100000){
        launch {
            delay(2000L)
            print("aa ")
        }
    }
}*/
//--------------------TEST08
/*fun main() = runBlocking {
    val job = launch {
        repeat(1000){
            if(isActive){
                println("job: I'm sleeping $it")
                delay(500L)
            }

        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancel()
    *//*job.join()*//*

    println("main: Now I can quit.")
}*/

//--------------------TEST09
/*fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i<5){
            if(System.currentTimeMillis()>=nextPrintTime){
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    job.cancelAndJoin()
    println("main: Now I can quit.")
}*/

//--------------------TEST10
/*fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}*/

//--------------------TEST11
/*
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}*/

//--------------------TEST12
/*fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}*/

//--------------------TEST13
/*fun main() = runBlocking {
    withTimeout(1300L){
        repeat(1000){
            println("I'm sleeping $it ...")
            delay(500)
        }
    }
}*/

//--------------------TEST14
/*fun main() = runBlocking {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
    println("Result is $result")
}*/

//--------------------TEST15
var acquired = 0

class Resource {
    init { acquired+=2 } // Acquire the resource
    fun close() { acquired-=1 } // Release the resource
}

fun main() {
    runBlocking {
        repeat(100_000) { // Launch 100K coroutines
            launch {
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50) // Delay for 50 ms
                    Resource()// Acquire a resource and return it from withTimeout block
                }
                resource.close() // Release the resource
            }
        }
    }
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}
//--------------------TEST16
/*var acquired = 0
class Resource {
    init { acquired+=2 } // Acquire the resource
    fun close() { acquired-=1 } // Release the resource
}
fun main(){
    runBlocking {
        repeat(100_000) { // Launch 100K coroutines
            launch {
                var resource: Resource? = null // Not acquired yet
                try {
                    withTimeout(60) { // Timeout of 60 ms
                        delay(50) // Delay for 50 ms
                        resource = Resource() // Store a resource to the variable if acquired
                    }
                    // We can do something else with the resource here
                } finally {
                    resource?.close() // Release the resource if it was acquired
                }
            }
        }
    }
    println(acquired) // Print the number of resources still acquired
}*/

//--------------------TEST17
//--------------------TEST18

