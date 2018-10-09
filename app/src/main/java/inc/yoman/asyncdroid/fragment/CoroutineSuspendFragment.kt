package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.R.id.button_retry
import kotlinx.android.synthetic.main.fragment_sub_sample.*
import kotlinx.coroutines.experimental.delay
import kotlin.coroutines.experimental.buildSequence

class CoroutineSuspendFragment: Fragment() {

    private var TAG = "CoroutineSuspendFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_retry.setOnClickListener { testSuspend() }
    }

    fun testSuspend() {
        val sequence = buildSequence {
            println("one")
            yield(1)

            println("two")
            yield(2)

            println("three")
            yield(3)

            println("done")
        }

        for(value in sequence) {
            println(value)
        }
    }
}