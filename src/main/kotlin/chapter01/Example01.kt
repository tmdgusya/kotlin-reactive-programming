package chapter01

/**
 * 기존의 반복자(iterator) 를 이용한 패턴
 */
fun main() {
    var list = listOf("One", 2, "Three", "Four", 4.5, "Five")

    var iterator = list.iterator()

    /**
     * Iterator 를 도는 동안 Thread 는 Blocking 된다.
     */
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}
