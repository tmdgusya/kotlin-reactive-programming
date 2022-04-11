package chapter01

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

fun main() {
    var subject: Subject<Int> = PublishSubject.create()

    subject.map { isEven(it) }.subscribe {
        println("The number is ${(if (it) "Even" else "Odd")}")
    }

    subject.onNext(4)
    subject.onNext(5)
    subject.onComplete()
}

fun isEven(number: Int): Boolean = number % 2 == 0