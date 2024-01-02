import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger


/*

private var counter = AtomicInteger()

fun main() = runBlocking{
    massiveRun{
        counter.incrementAndGet()
    }
    println(counter.get())
}

suspend fun massiveRun(action: suspend () -> Unit){
    withContext(Dispatchers.Default){
        repeat(1000){
            launch {
                action()
            }
        }
    }
}

 */

val mutex = Mutex()
private var counter = 0

fun main() = runBlocking{
    massiveRun{
        mutex.withLock {
            counter++
        }
    }
    println(counter)
}

suspend fun massiveRun(action: suspend () -> Unit){
    withContext(Dispatchers.Default){
        repeat(1000){
            launch {
                action()
            }
        }
    }
}