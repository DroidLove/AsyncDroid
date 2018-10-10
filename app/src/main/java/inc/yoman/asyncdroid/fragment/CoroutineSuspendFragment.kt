package inc.yoman.asyncdroid.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.fragment_suspend_function.*
import kotlinx.coroutines.experimental.*
import java.util.*

class CoroutineSuspendFragment: Fragment() {

    private var TAG = "CoroutineSuspendFragment"

    private var raceEnd = false
    private var greenJob: Job? = Job()
    private var redJob: Job? = null
    private var blueJob: Job? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_suspend_function, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonStart.setOnClickListener {
            startUpdate()
        }
    }

    private fun startUpdate() {
        resetRun()

        greenJob = GlobalScope.launch(Dispatchers.Main) {
            startRunning(progressBarGreen)
        }

        redJob = GlobalScope.launch(Dispatchers.Main) {
            startRunning(progressBarRed)
        }

        blueJob = GlobalScope.launch(Dispatchers.Main) {
            startRunning(progressBarBlue)
        }
    }

    private suspend fun startRunning(progressBar: RoundCornerProgressBar) {
        progressBar.progress = 0f
        calculateProgress(progressBar)
        postRaceResult(progressBar)
    }

    private fun postRaceResult(progressBar: RoundCornerProgressBar) {
        if (!raceEnd) {
            raceEnd = true

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Toast.makeText(context, "${progressBar.tooltipText} won!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun calculateProgress(progressBar: RoundCornerProgressBar) {
        while (progressBar.progress < 1000 && !raceEnd) {
            delay(10)
            progressBar.progress += (1..10).random()
        }
    }

    fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) +  start

    private fun resetRun() {
        raceEnd = false
        greenJob?.cancel()
        blueJob?.cancel()
        redJob?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        resetRun()
    }
}