import kotlinx.coroutines.*

/*
suspend fun main(){
    coroutineScope {
        //빌더로 생성된 잡은
        val job = Job()
        println(job) //JobImpl{Active}
        job.complete()
        println(job) //JobImpl{Completed}@5e9f23b4

        //launch로 생성된 잡은 JobImpl{Active} 상태
        val activeJob = launch{
            delay(1000L)
        }
        println(activeJob)  // StandaloneCoroutine{Active}@5e9f23b4
        // 여기서 잡이 완료될 때까지 기다립니다.
        activeJob.join()
        println(activeJob) // StandaloneCoroutine{Completed}@5e9f23b4

        val lazyJob = launch(start = CoroutineStart.LAZY){
            delay(1000L)
        }
        println(lazyJob) // LazyStandaloneCoroutine{New}@5e9f23b4
        lazyJob.start()
        println(lazyJob) // LazyStandaloneCoroutine{Active}@5e9f23b4
        lazyJob.join()
        println(lazyJob) // LazyStandaloneCoroutine{Completed}@5e9f23b4
    }
}


 */

/*
fun main(){
    runBlocking {
        val name = CoroutineName("MyCoroutine")
        val job = Job()

        launch (name + job) {
            val childName = coroutineContext[CoroutineName]
            println(childName == name) //true

            val childJob = coroutineContext[Job]
            println(childJob == job)    //false
            println(childJob == job.children.first()) //true
        }
    }
}
 */

/*
fun main(){
    runBlocking {
        val job1 = launch{
            delay(1000)
            println("test1")
        }

        val job2 = launch{
            kotlinx.coroutines.delay(1000)
            println("test2")
        }

        val parentJob:Job = coroutineContext.job
        println(job1 == parentJob) //false
        val parentChildren: Sequence<Job> = parentJob.children
        println(parentChildren.first() == job1) //true
        println(parentChildren.last() == job2) //true
    }
}

 */

suspend fun main(){
    coroutineScope {
        val job = Job()
        launch (job){
            customDelay(1000L)
            println("test1")
        }

        launch (job){
            kotlinx.coroutines.delay(2000L)
            println("test2")
        }

        job.complete()
        job.join()
    }
}