

fun main(){
    val seq = sequenceOf(1,2,3)
    val filtered = seq.filter { println("f$it "); it %2 == 1 }
    println(filtered)

    val asList = filtered.toList()
    println(asList)

    // Sequence is lazy
    sequenceOf(1,2,3)
        .filter { print("f$it "); it %2 == 1 }
        .map { print("m$it "); it * 2 }
        .forEach { print("E$it, ") }

    println()

    // List is eager
    listOf(1,2,3)
        .filter { print("f$it "); it %2 == 1 }
        .map { print("m$it "); it * 2 }
        .forEach { print("E$it, ") }

    println("sequence 를 사용하면 최소 연산을 할 수 있음.")

    (1..10).asSequence()
        .filter { print("f$it "); it %2 == 1 }
        .map { print("m$it "); it * 2 }
        .find { it > 5 }
    println()

    (1..10)
        .filter { print("f$it "); it %2 == 1 }
        .map { print("m$it "); it * 2 }
        .find { it > 5 }

    println()
    println("무한 sequence ")
    generateSequence(1) { it + 1 }
        .map { it * 2 }
        .take(10)
        .forEach { print("$it ") }

    println()

    val fibonacci = sequence {
        yield(1)
        var current = 1
        var prev = 1
        while(true){
            yield(current)
            val tmp = prev
            prev = current
            current += tmp
        }
    }

    println(fibonacci.take(10).toList())
    //println(fibonacci.toList()) 무한 SEQUENCE
    println("test")
}