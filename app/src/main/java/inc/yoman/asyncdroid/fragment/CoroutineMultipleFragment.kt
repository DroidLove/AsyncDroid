package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.fragment_sub_sample.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class CoroutineMultipleFragment: Fragment() {

    private var TAG = "CoroutineWithLaunchFragment"

    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_retry.setOnClickListener {
//            startCoroutine()
            startThread()
        }
    }

    private fun startCoroutine() {
        runBlocking(bgDispatcher) {
            val jobs = List(100000) {
                launch {
                    delay(100L)
                    Log.e("Coroutinue Count", "* "+ it.toString() + "Process: ${Thread.currentThread()}")
                }
            }
            jobs.forEach { it.join() }
        }
    }

    private fun startThread() {
            val threads = List(100000) {
                thread {
                    Thread.sleep(1000L)
                    Log.e("Thread Count", "* "+ it.toString() + "Process: ${Thread.currentThread()}")
                }
            }
        threads.forEach { it.join() }
    }

    private fun printResult(output: String) {
        textView_result.append(output)
    }
}