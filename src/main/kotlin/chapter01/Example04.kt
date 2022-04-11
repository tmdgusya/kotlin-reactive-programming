package chapter01

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

fun main() {
    var calculator = ReactiveCalculator(15, 10)
    println("Enter a = <number> or b = <number> in separate lines\nexit to exit the program")

    var line: String?

    do {
        line = readLine()
        calculator.handleInput(line)
    } while (line != null && !line.toLowerCase().contains("exit") )

}

class ReactiveCalculator(a: Int, b: Int) {
    internal val subjectAdd: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectSub: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectMult: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectDiv: Subject<Pair<Int, Int>> = PublishSubject.create()

    internal val subjectCalc: Subject<ReactiveCalculator> = PublishSubject.create()

    internal var nums = Pair(0, 0)

    init {
        nums = Pair(a, b)

        subjectAdd.map { it.first + it.second }.subscribe { println("Add $it") }
        subjectSub.map { it.first - it.second }.subscribe { println("Sub $it") }
        subjectMult.map { it.first * it.second }.subscribe { println("Mult $it") }
        subjectDiv.map { it.first / it.second }.subscribe { println("Div $it") }

        subjectCalc.subscribe {
            with(it) {
                calculateAddition()
                calculateSubstraction()
                calculateMultiplication()
                calculateDivision()
            }
        }
    }

    fun calculateAddition() {
        subjectAdd.onNext(nums)
    }

    fun calculateSubstraction() {
        subjectSub.onNext(nums)
    }

    fun calculateMultiplication() {
        subjectMult.onNext(nums)
    }

    fun calculateDivision() {
        subjectDiv.onNext(nums)
    }

    fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(this)
    }

    fun handleInput(inputLine: String?) {
        if (!inputLine.equals("exit")) {
            val pattern: Pattern = Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)")

            var x: Int? = null
            var y: Int? = null

            val matcher: Matcher = pattern.matcher(inputLine)

            if (matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(1).lowercase(Locale.getDefault()) == "a") {
                    x = matcher.group(2).toInt()
                } else if (matcher.group(1).lowercase(Locale.getDefault()) == "b") {
                    y = matcher.group(2).toInt()
                }
            }

            when {
                x != null && y != null -> modifyNumbers(x, y)
                x != null -> modifyNumbers(a = x)
                y != null -> modifyNumbers(b = y)
                else -> println("Invalid Input")
            }
        }
    }
}
