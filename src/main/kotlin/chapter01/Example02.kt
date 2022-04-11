package chapter01

import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main() {
    var list = listOf("One", 2, "Three", "Four", 4.5, "Five")

    /**
     * 비동기 데이터 스트림
     */
    val observable = list.toObservable()

    /**
     * observable 를 구독한다.
     *
     * 모든 변경사항은 onNext 로 하나씩 푸시된다.
     * 데이터가 다 전송되면 onComplete 가 호출된다.
     * 중간에 실패하면 onError 가 호출된다.
     */
    observable.subscribeBy(
        onNext = { println(it) },
        onError = { it.printStackTrace() },
        onComplete = { println("Done!") }
    )
}