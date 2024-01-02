import kotlinx.coroutines.test.TestCoroutineScheduler

fun main(){
    val scheduler = TestCoroutineScheduler()
    println(scheduler.currentTime)
    scheduler.advanceTimeBy(1000)
    println(scheduler.currentTime)
    scheduler.advanceTimeBy(1000)
    println(scheduler.currentTime)
}

