import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val resultDeferred: Deferred<Int> = async {
            println("2.async threadName[${Thread.currentThread().name}]")
            customDelay(5000L)
            42
        }

        launch {
            println("3.launch start threadName[${Thread.currentThread().name}]")
            delay(1000L)
            println("4.launch end threadName[${Thread.currentThread().name}]")
        }

        println("1.runBlocking scope threadName[${Thread.currentThread().name}]")
        println("언제 실행??? threadName[${Thread.currentThread().name}]")

        //다른 작업을 합니다...
        val result: Int = resultDeferred.await() //1초후
        println("5.async result : $result threadName[${Thread.currentThread().name}]") //42
        //다음과 같이 간단하게 작성할 수도 있습니다.
        println("6.result deferred : ${resultDeferred.await()} threadName[${Thread.currentThread().name}]") //42

        launch {
            delay(10000L)
            println("7.launch end threadName[${Thread.currentThread().name}]")
        }
    }

    println("=========================================================================================")

    runBlocking {
        println("1.runBlocking start")

        launch {
            println("2.launch")
            delay(5000L)
            println("4.runBlocking")
        }

        launch {
            println("3.launch")
            delay(5000L)
            println("5.runBlocking2")
        }
    }
}
