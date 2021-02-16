package com.diana.primshits.coroutine

import kotlinx.coroutines.*
import org.junit.Test
import java.lang.Thread.sleep
import kotlin.coroutines.CoroutineContext

class CoroutineExceptionHandlingTest {

    @Test
    fun innerCoroutineTest() {

        class ParentExceptionHandler : CoroutineExceptionHandler {
            override val key: CoroutineContext.Key<*>
                get() = CoroutineExceptionHandler.Key

            override fun handleException(context: CoroutineContext, exception: Throwable) {
                println("handle in parent handler")
            }
        }

        class ChildExceptionHandler : CoroutineExceptionHandler {
            override val key: CoroutineContext.Key<*>
                get() = CoroutineExceptionHandler.Key

            override fun handleException(context: CoroutineContext, exception: Throwable) {
                println("handle in child handler")
            }
        }

        CoroutineScope(SupervisorJob() + ParentExceptionHandler())
            .launch {

                supervisorScope {
                    CoroutineScope(SupervisorJob()).launch(ChildExceptionHandler()) {
                        println("first inner start + ${coroutineContext.job}")
                        delay(1000)
                        10 / 0
                        println("first inner end")
                    }

                    launch(ChildExceptionHandler()) {
                        println("second inner start + ${coroutineContext.job}")
                        delay(2000)
                        10 / 0
                        println("second inner end")
                    }

                    launch(ChildExceptionHandler()) {
                        println("third inner start + ${coroutineContext.job}")
                        delay(6000)
                        println("third inner end")
                    }
                }
            }

        sleep(7000)
        println("end")
    }

    @Test
    fun innerCoroutineTest2() {

        class ParentExceptionHandler : CoroutineExceptionHandler {
            override val key: CoroutineContext.Key<*>
                get() = CoroutineExceptionHandler.Key

            override fun handleException(context: CoroutineContext, exception: Throwable) {
                println("handle in handler")
            }
        }

        val scope = CoroutineScope(Job() + ParentExceptionHandler())

        scope.launch {
            delay(100)
            10 / 0
            println("1 inner end")
        }

        scope.launch {
            delay(200)
            10 / 0
            println("2 inner end")
        }

        scope.launch {
            delay(300)
            println("3 inner end")
        }

        sleep(600)
        println("end")
    }
}