import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf

suspend fun main() {
    val f = flowOf("A", "B", "C")
    f.collect { print(it) } // ABC

    listOf(1,2,3,4,5).asFlow().collect { print(it) }
}

