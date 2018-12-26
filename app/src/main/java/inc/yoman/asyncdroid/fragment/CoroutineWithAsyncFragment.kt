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

class CoroutineWithAsyncFragment: Fragment() {

    private var TAG = "CoroutineWithLaunchFragment"

    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_retry.setOnClickListener {
            startCoroutine()
        }
    }

    private fun startCoroutine() {
        GlobalScope.launch(uiDispatcher) {
            var task1 = GlobalScope.async(Dispatchers.Main) {
                delay(2000)
                return@async "Hello"
            }

            var task2 = GlobalScope.async(Dispatchers.Main) {
                delay(5000)
                return@async " Coroutine!"
            }

            printResult(task1.await() + task2.await())
        }
    }

    private fun printResult(output: String) {
        textView_result.append(output)
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
}