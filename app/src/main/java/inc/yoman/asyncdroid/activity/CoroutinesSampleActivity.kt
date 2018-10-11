package inc.yoman.asyncdroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.activity_coroutines_sample.*
import org.jetbrains.anko.intentFor

class CoroutinesSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_sample)

        button_coroutines_using_launch.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 6)) }

        button_coroutines_using_async.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 7)) }

        button_cancel_coroutine.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 8)) }

        button_suspend_function.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 9)) }

        button_multiple_coroutine.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 10)) }

        button_http_api_calls.setOnClickListener { startActivity(intentFor<ApiCallingActivity>()) }
    }
}
