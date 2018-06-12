package inc.yoman.rxjavasample.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import inc.yoman.rxjavasample.R
import inc.yoman.rxjavasample.fragment.APICallCoroutinesFragment
import inc.yoman.rxjavasample.fragment.APICallHelperCoroutinesFragment
import inc.yoman.rxjavasample.fragment.APICallRxJavaFragment
import inc.yoman.rxjavasample.fragment.BasicRxJavaSampleFragment
import kotlinx.android.synthetic.main.activity_sub_sample.*

class SubSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_sample)

        if (intent.hasExtra("frag_id")) {
            when (intent.getIntExtra("frag_id", 0)) {
                1 -> {
                    openFragment(BasicRxJavaSampleFragment())
                    supportActionBar?.title = "APICall AsyncTask"
                }
                2 -> {
                    openFragment(APICallRxJavaFragment())
                    supportActionBar?.title = "APICall RxJava"
                }
                3 -> {
                    openFragment(APICallCoroutinesFragment())
                    supportActionBar?.title = "APICall Coroutines"
                }
                4 -> {
                    openFragment(APICallHelperCoroutinesFragment())
                    supportActionBar?.title = "APICall Helper Coroutines"
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(constraint_layout_fragment.id,
                        fragment).commit()
    }
}
