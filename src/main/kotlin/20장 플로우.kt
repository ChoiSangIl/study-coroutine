fun interface FlowCollector {
    suspend fun emit(value: String)
}
interface Flow {
    suspend fun collect(collector: FlowCollector)
}
suspend fun main() {
    val builder: suspend FlowCollector.()-> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }

    val flow: Flow = object : Flow {
        override suspend fun collect(
            collector: FlowCollector
        ) {
            collector.builder()
        }
    }
    flow.collect { print(it) } // ABC
    flow.collect { print(it) } // ABC
}

/*
fun interface FlowCollector<T> {
    suspend fun emit(value: T)
}
interface Flow<T> {
    suspend fun collect(collector: FlowCollector<T>)
}

fun <T> flow(
    builder: suspend FlowCollector<T>.()-> Unit
) = object : Flow<T> {
    override suspend fun collect(
        collector: FlowCollector<T>
    ) {
        collector.builder()
    }
}
suspend fun main() {
    val f: Flow<String> = flow {
        emit("A")
        emit("B")
        emit("C")
    }
    f.collect { print(it) } // ABC
    f.collect { print(it) } // ABC
}


 */