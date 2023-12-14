import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val executor = Executors.newSingleThreadScheduledExecutor(){
    Thread(it, "scheduler").apply { isDaemon = true }
}

suspend fun customDelay(timeMillis: Long): Unit =
    suspendCoroutine { continuation ->
        executor.schedule({
            continuation.resume(Unit)
        }, timeMillis, java.util.concurrent.TimeUnit.MILLISECONDS)
    }
suspend fun main(){
    runBlocking {
        launch {
            bart_test1()
        }

        launch {
            bart_test2()
        }
    }
}

//중단 함수 before 이후에 호출되지 않는다. 코루틴이 중단 되고 resume 되지 않기 때문에
suspend fun test3_1(){
    println("test3_1 Before")
    suspendCoroutine<Unit> { continuation ->
        continuation.resume(Unit)
    }
    println("test3_1 After")
}

//잠깐 동안 정지(sleep)된 뒤 재개되는 다른 스레드를 실행할 수도 있습니다.
/*
out:::
Before
Suspended
After
Resumed
 */
suspend fun test3_2(){
    println("test3_2 Before")
    suspendCoroutine<Unit> { continuation ->
        thread{
            println("test3_2 Suspended")
            Thread.sleep(10000)
            continuation.resume(Unit)
            println("test3_2 Resumed")
        }
    }
    println("test3_2 After")
}

suspend fun bart_test1(){
    println("bart_test1 Before")
    println(Thread.currentThread().name)
    delay(1000)
    println(Thread.currentThread().name)
    println("bart_test1 After")
}

suspend fun bart_test2(){
    println("bart_test2 Before")
    println(Thread.currentThread().name)
    println("bart_test2 After")
}

