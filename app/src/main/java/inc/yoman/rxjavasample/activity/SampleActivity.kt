package inc.yoman.rxjavasample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import inc.yoman.rxjavasample.R


class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

//        button_basic.setOnClickListener { startActivity(intentFor<SubSampleActivity>("frag_id" to 1)) }

    }
}
