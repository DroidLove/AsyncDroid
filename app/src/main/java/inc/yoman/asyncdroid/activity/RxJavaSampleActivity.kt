package inc.yoman.asyncdroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.activity_sample.*
import org.jetbrains.anko.intentFor


class RxJavaSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        button_basic.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 1)) }

        button_rx_java_disposable.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 2)) }

        button_rx_java_operator.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 3)) }

        button_rx_java_compositeDisposable.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 4)) }

        button_rx_java_sample_compact.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 5)) }

        button_api_calls.setOnClickListener { startActivity(intentFor<ApiCallingActivity>()) }
    }
}
