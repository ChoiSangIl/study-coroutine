import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

suspend fun main(){
    //chapter11Test1()
    /*
    runBlocking(CoroutineName("MyCoroutine")) {
        println("before")
        chapter11Test2()
        println("after")
    }
     */

    /*
    runBlocking {
        val job = launch(CoroutineName("launch")) {
            chapter11Test3()
        }
        delay(1500)
        job.cancel()
    }
     */

    /*
    runBlocking {
        println("before")
        chapter11Test4()
        println("after")
    }
     */

    chapter11Test5()
}

suspend fun chapter11Test1() = runBlocking {
    val a = coroutineScope {
        delay(3000)
        10
    }
    println("a is caculated")
    val b = coroutineScope {
        delay(3000)
        20
    }
    println(a)
    println(b)
}

suspend fun chapter11Test2() = coroutineScope{
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 1")
    }
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 2")
    }
}

suspend fun chapter11Test3() = coroutineScope{
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 1")
    }
    launch {
        delay(2000)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 2")
    }
}

suspend fun chapter11Test4() = supervisorScope{
    launch {
        delay(1000)
        throw Error()
    }
    launch {
        delay(2000)
        println("test2")
    }
}

suspend fun chapter11Test5() = runBlocking {
    println("before")

    withContext(SupervisorJob()){
        launch {
            delay(1000)
            throw Error()
        }
        launch {
            delay(2000)
            println("test2")
        }
    }

    println("after")
}