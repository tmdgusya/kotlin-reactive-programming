package chapter02

fun doSomeStuff(a: Int = 0) = a + (a * a)

fun inlineDoSomeStuff(a: Int = 0) = a + (a * a)

fun main() {
    for (i in 1..10) {
        println("$i Output ${ doSomeStuff(i)}")

        /**
         * inline 방식이 더 효율적임
         */
        println("$i Output ${ inlineDoSomeStuff(i)}")
    }
}
