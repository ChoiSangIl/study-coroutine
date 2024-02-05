import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main(){

    println("================================onEach==============================================")

    // onEach 는 중단 함수 이며 원소를 방출할 때마다 호출된다.
    flowOf(1,2,3,4)
        .onEach { println("onEach $it") }
        .collect()

    flowOf(1,2,3,4)
        .onEach { delay(100) }
        .collect{ println("collect $it")}

    println("================================onStart==============================================")

    /**
     * onStart 는 첫 번째 원소가 샛엉되는 걸 기다렸다 호출되는 게 아니다. 첫 번째 원소를 요청했을 때 호출되는 함수
     * out
     * onStart : Before
     * onEach : call
     * collect 1
     * onEach : call
     * collect 2
     */
    flowOf(1, 2)
        .onEach { println("onEach : call"); delay(100) }
        .onStart { println("onStart : Before") }
        .collect { println("collect $it") }

    println("=========================================================================================")

    flowOf(1,2)
        .onEach { println("onEach : call"); delay(100) }
        .onStart { emit(0) }
        .collect { println("collect $it") }

    println("================================onCompleted==============================================")
    
    coroutineScope {
        flowOf(1,2,3,4)
            .onEach { println("onEach : call"); delay(100) }
            .onCompletion { println("Completed") }
            .collect { println("collect $it") }
    }

    println("================================onEmpty==============================================")
    coroutineScope {
        flow<List<Int>> { delay(1000) }
            .onEmpty { emit(emptyList()) }
            .collect{ println(it)}
    }

    println("================================lunchIn==============================================")

    coroutineScope {
        flowOf("USER1", "USER2", "USER3")
            .onStart { println("Users:") }
            .onEach { println(it) }
            .launchIn(this)
    }


}
