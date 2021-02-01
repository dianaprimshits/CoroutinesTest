package com.diana.primshits.coroutine

import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Thread.sleep

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testFirst() {
        runBlocking {
            launch {
                sleep(500)
                println("first job")
            }

            coroutineScope {

                launch {
                    delay(200)
                    println("first inner job")
                }

                launch {
                    sleep(300)
                    println("second inner job")
                }
            }
        }
    }

    @Test
    fun testSecond() {
        CoroutineScope(Dispatchers.IO).launch {
            println("first job")

            val job = coroutineScope {
                launch {

                    launch {
                        delay(300)
                        println("first inner job1")
                    }

                    delay(200)
                    println("first inner job2")
                }
            }

            job.cancel()

            launch {
                delay(800)
                println("second job")
            }
        }
    }

    @Test
    fun testThird() {
        runBlocking {
            launch(Dispatchers.Default) {

                supervisorScope {
                    delay(200)

                    launch {
                        println("inner job: ${coroutineContext.job}")

                        launch {
                            println("inner inner job: ${coroutineContext.job}")
                        }
                    }

                    this.cancel()
                    println("cancel")
                }
            }
        }
    }

    @Test
    fun testFourth() {
        runBlocking {
            val job = launch(Dispatchers.Default) {
                for (value in 0..5) {
                    coroutineScope {
                        if (!isActive) {
                            println("help")
                            this.cancel()
                        }
                    }

                    sleep(30)
                    println("ready for the next one?")
                }

            }
            delay(40)
            job.cancelAndJoin()
        }
    }

    @Test
    fun testFifth() {
        CoroutineScope(Job()).launch {
            println("start")

            val job = launch {
                delay(500)
                println("first")

                launch {
                    println("inner first")
                }

                launch {
                    println("inner second")
                }
            }

            launch(Dispatchers.IO) {
                println("second")
            }

            delay(400)
            job.cancel()
        }


        sleep(600)
        println("end")
    }
}
