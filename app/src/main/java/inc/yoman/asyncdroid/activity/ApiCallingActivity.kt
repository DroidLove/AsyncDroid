package inc.yoman.asyncdroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import inc.yoman.asyncdroid.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class ApiCallingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_async_task.setOnClickListener { startActivity(intentFor<ApiListingActivity>("frag_id" to 1)) }

        button_rx_java.setOnClickListener { startActivity(intentFor<ApiListingActivity>("frag_id" to 2)) }

        button_coroutines.setOnClickListener { startActivity(intentFor<ApiListingActivity>("frag_id" to 3)) }

        button_helper_coroutines.setOnClickListener { startActivity(intentFor<ApiListingActivity>("frag_id" to 4)) }

        button_coroutines_dsl.setOnClickListener { startActivity(intentFor<ApiListingActivity>("frag_id" to 5)) }
    }
}
