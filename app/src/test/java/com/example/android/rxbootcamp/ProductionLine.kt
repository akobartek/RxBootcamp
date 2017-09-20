package com.example.android.rxbootcamp

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class ProductionLine {

    @Test
    fun test() {
        val testObserver = getInitials("imie imie nazwisko")

        testObserver.assertValues("I.I.N.")
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }
}

fun getInitials(name: String):TestObserver<String> {
    return Observable.just(name)
            .map { it.trim().split(" ") }
            .flatMapIterable { it }
            .map { it[0] }
            .map { it.toUpperCase() }
            .toList()
            .map { it.joinToString(separator = ".", postfix = ".") }
            .toObservable()
            .test()
}