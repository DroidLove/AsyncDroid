package inc.yoman.asyncdroid.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.fragment.*
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
                5 -> {
                    openFragment(BasicRxJavaCompactSampleFragment())
                    supportActionBar?.title = resources.getString(R.string.compact_sample)
                }
                6 -> {
                    openFragment(CoroutineWithLaunchFragment())
                    supportActionBar?.title = resources.getString(R.string.coroutine_using_launch)
                }
                7 -> {
                    openFragment(CoroutineWithAsyncFragment())
                    supportActionBar?.title = resources.getString(R.string.coroutine_using_async)
                }
                8 -> {
                    openFragment(CoroutineCancellationFragment())
                    supportActionBar?.title = resources.getString(R.string.cancel_coroutine)
                }
                9 -> {
                    openFragment(CoroutineSuspendFragment())
                    supportActionBar?.title = resources.getString(R.string.suspend_function)
                }
                10 -> {
                    openFragment(CoroutineMultipleFragment())
                    supportActionBar?.title = resources.getString(R.string.multiple_coroutine)
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
