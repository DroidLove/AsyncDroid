package inc.yoman.asyncdroid.fragment

import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.dsl.logd
import inc.yoman.asyncdroid.dsl.loge
import kotlinx.android.synthetic.main.fragment_sub_sample.*
import kotlinx.coroutines.experimental.*

class CoroutineWithLaunchFragment: Fragment() {

    private var TAG = "CoroutineWithLaunchFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_retry.setOnClickListener {
            basicCoroutineTwo()
//            basicCoroutine()
//            println(Thread.currentThread())
//
//            val latch = java.util.concurrent.CountDownLatch(1)
//
//            GlobalScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT, {
//                println(process(2))
//                latch.countDown()
//            })
//
//            latch.await()
        }
    }

    fun process(n: Int): Int {
        println("process: ${Thread.currentThread()}")
        return n * 2;
    }

    fun basicCoroutine() {
        var currentThread = "Process : ${Thread.currentThread()}"

        textView_result.text = currentThread
        GlobalScope.launch {
            delay(5000)
            Log.d("Example", "World!")
            withContext(Dispatchers.Main) {textView_result.append("Process: ${Thread.currentThread()}") }
        }
        Log.d("Example", "Hello")
//        Thread.sleep(5000)
    }

    fun basicCoroutineTwo() {
        GlobalScope.launch(Dispatchers.Main) {
            var task1 = GlobalScope.async(Dispatchers.Main) {
                delay(2000)
                return@async "Hello"
            }

            var task2 = GlobalScope.async(Dispatchers.Main) {
                delay(5000)
                return@async " World!"
            }

            printResult(task1.await() + task2.await())
        }
    }

    fun printResult(output: String) {
        textView_result.append(output)
    }
}