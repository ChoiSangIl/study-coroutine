import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val executor = Executors.newSingleThreadScheduledExecutor(){
    Thread(it, "scheduler").apply { isDaemon = true }
}

suspend fun delay(timeMillis: Long): Unit =
    suspendCoroutine { continuation ->
        executor.schedule({
            continuation.resume(Unit)
        }, timeMillis, java.util.concurrent.TimeUnit.MILLISECONDS)
    }
suspend fun main(){
    println("Before")
    delay(1000)
    println("After")
}

