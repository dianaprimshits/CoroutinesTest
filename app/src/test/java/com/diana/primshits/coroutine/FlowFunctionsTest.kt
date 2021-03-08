package com.diana.primshits.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlowFunctionsTest {

    @Test
    fun testFirst() {
        runBlocking {
            val values: Flow<Int> = flow {
                for (value in 1..10) {
                    delay(100)
                    emit(value)
                    println("emited value: $value")
                }
            }

            values.collect { println(it) }
        }
    }

    @Test
    fun testSecond() {
        runBlocking {
            val values: Flow<Int> = flow {
                for (value in 1..10) {
                    delay(100)
                    emit(value)
                    println("emited value: $value")
                }
            }

            println("result: ${values.filter { value -> value == 5 }.single()}")
        }
    }

    @Test
    fun testThird() {
        runBlocking {
            val values: Flow<Int> = flow {
                for (i in 1..10) {
                    delay(100)
                    emit(i)
                    println("emited value: $i")
                }
            }

            println(
                values.map { value -> value * 10 }
                    .reduce { accumulator, value -> value + accumulator * 2 }
            )
        }
    }
}