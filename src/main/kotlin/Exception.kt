import kotlinx.coroutines.*

/**
 * 코틀린 코루틴 10장 예외처리
 */

suspend fun main() {
    //test_10_1()
    //test_10_2()
    //test_10_3()
    test_10_4()
}

suspend fun test_10_1(): Unit = runBlocking {
    launch {
        println("1.첫번째로 호출되겠지?")
        launch {
            delay(1000L)
            throw Error("Some Error")
        }

        launch {
            delay(2000L)
            println("Some Work")
        }

        launch {
            delay(500)
            println("will be printed")
        }
    }

    launch {
        println("2 두번째로 호출되겠지?")
        delay(2000)
        println("will not be printed")
    }

    println("0.첫번째로 호출되겠지?")
}

suspend fun test_10_2(): Unit = runBlocking {
    launch {
        println("1.첫번째로 호출되겠지?")

        try {
            launch {
                delay(1000L)
                throw Error("Some Error")
            }
        } catch (e: Error) {
            println("will not be printed")
        }
    }

    launch {
        println("2.두번째로 호출되겠지?")
        delay(2000)
        println("will not be printed")
    }
}

suspend fun test_10_3(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

    scope.launch {
        launch {
            delay(1000L)
            throw Error("Some Error")
        }
    }

    scope.launch {
        delay(2000L)
        println("will be printed")
    }

    //delay(3000L)
    println("run blocking 종료")
}

suspend fun test_10_4(): Unit = runBlocking {

    launch ( SupervisorJob() ){
        launch() {
            delay(1000L)
            throw Error("Some Error")
        }

        launch {
            delay(2000L)
            println("will be not printed")
        }
    }
    delay(3000L)
    println("run blocking 종료")
}