import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


fun main(){
    runBlocking {
        TestScope(Dispatchers.IO).launch {
            println("test")
        }
    }
}
fun TestScope(
    context: CoroutineContext
): CoroutineScope =
    ContextScope(
        if (context[Job] != null) context
        else context + Job()
    )
internal class ContextScope(
    context: CoroutineContext
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = context
    override fun toString(): String =
        "CoroutineScope(coroutineContext=$coroutineContext)"
}
