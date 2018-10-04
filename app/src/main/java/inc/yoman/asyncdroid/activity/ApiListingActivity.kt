package inc.yoman.asyncdroid.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.fragment.*
import kotlinx.android.synthetic.main.activity_second.*

class ApiListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (intent.hasExtra("frag_id")) {
            when (intent.getIntExtra("frag_id", 0)) {
                1 -> {
                    openFragment(APICallAsyncFragment())
                    supportActionBar?.title = getString(R.string.api_asyncTask)
                }
                2 -> {
                    openFragment(APICallRxJavaFragment())
                    supportActionBar?.title = getString(R.string.api_rxjava)
                }
                3 -> {
                    openFragment(APICallCoroutinesFragment())
                    supportActionBar?.title = getString(R.string.api_coroutines)
                }
                4 -> {
                    openFragment(APICallHelperCoroutinesFragment())
                    supportActionBar?.title = getString(R.string.api_helper_coroutines)
                }
                5 -> {
                    openFragment(APICallCoroutinesWithDslFragment())
                    supportActionBar?.title = getString(R.string.api_dsl_coroutines)
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(constraint_layou_fragment.id,
                        fragment).commit()
    }
}
