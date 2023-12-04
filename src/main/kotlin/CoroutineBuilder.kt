import kotlinx.coroutines.*

fun main(){
     runBlocking {
        val resultDeferred: Deferred<Int> = GlobalScope.async {
            delay(5000L)
            42
        }

         //다른 작업을 합니다...
         val result: Int = resultDeferred.await() //1초후
         println(result) //42
         //다음과 같이 간단하게 작성할 수도 있습니다.
         println(resultDeferred.await()) //42
    }
}