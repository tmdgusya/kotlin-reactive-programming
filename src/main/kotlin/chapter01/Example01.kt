package chapter01

/**
 * 기존의 반복자(iterator) 를 이용한 패턴
 */
fun main() {
    var list = listOf("One", 2, "Three", "Four", 4.5, "Five")

    var iterator = list.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}
