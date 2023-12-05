import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main(){
    test3()
}

//job.join()을 호출하면 코루틴이 취소를 마칠 때까지 중단 되므로 경쟁 상태가 발생하지 않는다.
suspend fun test(): Unit = coroutineScope {
    val job = launch {
        repeat(1_000){i->
            kotlinx.coroutines.delay(100)
            Thread.sleep(100)
            println("Printing $i")
        }
    }

    kotlinx.coroutines.delay(1000)
    job.cancel()
    //job.join()
    println("Cancelled successfully")
}


//잡이 취소 되면 Cancelling 상태로 변경 되며 CancellationException이 발생한다.
suspend fun test2(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_000){i->
            try{
                kotlinx.coroutines.delay(200)
                println("Printing $i")
            }catch (e: CancellationException){
                println(e)
                throw e
            }
        }
    }

    kotlinx.coroutines.delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
    kotlinx.coroutines.delay(1000)
}

//invokeOnCompletion 을 통해 잡의 상태가 마지막 상태에 도착 했을 때 실행 되는 핸들러를 지정할 수 있다. BUT 어떤 쓰레드에서 실행할지 결정할 수는 없다.
suspend fun test3(): Unit = coroutineScope {
    val job = launch {
        kotlinx.coroutines.delay(Random.nextLong(2400))
        println("Finished")
        yield()
    }
    kotlinx.coroutines.delay(800)
    job.invokeOnCompletion { exception: Throwable? ->
        println("Will always be printed")
        println("exception was $exception")
    }
    kotlinx.coroutines.delay(800)
    job.cancelAndJoin()
}


/*
fun main(){
    runBlocking {
        coroutineScope {
            val job = launch {
                repeat(1_000){i->
                    kotlinx.coroutines.delay(200)
                    println("Printing $i")
                }
            }

            kotlinx.coroutines.delay(1100)
            job.cancel()
            println("Cancelled successfully")
        }
    }
}

 */