package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.fragment_cancel_coroutine.*
import kotlinx.coroutines.experimental.*

class CoroutineCancellationFragment : Fragment() {

    private var TAG = "CoroutineCancellationFragment"

    private var parentJob: Job? = null

    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cancel_coroutine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_start.setOnClickListener {
            startCoroutine()
        }

        button_stop.setOnClickListener {
            parentJob?.cancel()
            printResult("Coroutine Cancel: Stopped Increment")
        }
    }

    private fun startCoroutine() {
        parentJob = GlobalScope.async(bgDispatcher) {
            withContext(uiDispatcher) {
                for (i in 1..10) {
                    delay(1000)
                    printCountIncrement(i.toString())
                }
            }
        }

        parentJob?.invokeOnCompletion {
            if (it == null) {
                printResult("Coroutine Completed: Increment Done")
            } else {
                Log.e("Cancellation", parentJob?.getCancellationException().toString())
            }
        }

    }

    private fun printCountIncrement(output: String) {
        textView_count_increment.text = output
    }

    private fun printResult(output: String) {
        textView_result.text = (output)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentJob?.cancel()
    }
}