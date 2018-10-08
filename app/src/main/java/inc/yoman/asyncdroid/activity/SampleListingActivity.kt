package inc.yoman.asyncdroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import inc.yoman.asyncdroid.R

import kotlinx.android.synthetic.main.activity_sample_listing.*
import org.jetbrains.anko.intentFor

class SampleListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_listing)

        button_coroutines_samples.setOnClickListener { startActivity(intentFor<CoroutinesSampleActivity>()) }

        button_rx_java_samples.setOnClickListener { startActivity(intentFor<RxJavaSampleActivity>()) }

        button_http_api_calls.setOnClickListener { startActivity(intentFor<ApiCallingActivity>()) }

    }

}
