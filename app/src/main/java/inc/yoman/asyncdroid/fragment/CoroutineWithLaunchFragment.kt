package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.fragment_sub_sample.*
import kotlinx.coroutines.experimental.*

class CoroutineWithLaunchFragment: Fragment() {

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
        textView_result.text = ""
        GlobalScope.launch(uiDispatcher) { // launch new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            printResult("World!") // print after delay

            delay(1000)
            withContext(uiDispatcher) {

            }
            printResult("\n I am Coroutine")
        }
        printResult("Hello ") // main thread continues while coroutine is delayed
//        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    private fun printResult(output: String) {
        textView_result.append(output)
    }
}