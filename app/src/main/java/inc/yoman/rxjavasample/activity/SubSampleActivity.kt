package inc.yoman.rxjavasample.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import inc.yoman.rxjavasample.R
import inc.yoman.rxjavasample.R.id.constraint_layout_fragment
import inc.yoman.rxjavasample.fragment.*
import kotlinx.android.synthetic.main.activity_sub_sample.*

class SubSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_sample)

        if (intent.hasExtra("frag_id")) {
            when (intent.getIntExtra("frag_id", 0)) {
                1 -> {
                    openFragment(BasicRxJavaSampleFragment())
                    supportActionBar?.title = resources.getString(R.string.basic_example)
                }
                2 -> {
                    openFragment(RxJavaDisposableSampleFragment())
                    supportActionBar?.title = resources.getString(R.string.using_disposable)
                }
                3 -> {
                    openFragment(RxJavaOperatorSampleFragment())
                    supportActionBar?.title = resources.getString(R.string.using_operator)
                }
                4 -> {
                    openFragment(RxJavaCompositeSampleFragment())
                    supportActionBar?.title = resources.getString(R.string.using_compositedisposable)
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
