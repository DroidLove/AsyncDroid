package inc.yoman.asyncdroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.activity_sample.*
import kotlinx.android.synthetic.main.activity_sample_listing.*
import org.jetbrains.anko.intentFor

class CoroutinesSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_sample)

        button_coroutines_samples.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 6)) }

        button_rx_java_samples.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 7)) }

    }
}
