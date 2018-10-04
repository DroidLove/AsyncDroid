package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sub_sample.*

/**
 * Basic Observable, Observer, Subscriber example
 * Observable emits list of animal names
 * You can see filter() operator is used to filter out the
 * animal names that starts with letter `b`
 */
class RxJavaOperatorSampleFragment : Fragment() {

    private var TAG = "RxJavaOperatorSampleFragment"

    private lateinit var disposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callRxJava()

        button_retry.setOnClickListener { callRxJava() }
    }

    private fun callRxJava() {
        // observable
        val animalsObservable = getAnimalsObservable()

        // observer
        val animalsObserver = getAnimalsObserver()

        // observer subscribing to observable
        animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { s -> s.toLowerCase().startsWith("b") }
                .subscribeWith(animalsObserver)
    }

    private fun getAnimalsObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
                disposable = d
            }

            override fun onNext(s: String) {
                Log.d(TAG, "Name: $s")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "All items are emitted!")
            }
        }
    }

    private fun getAnimalsObservable(): Observable<String> {
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox")
    }

    override fun onDestroy() {
        super.onDestroy()

        // don't send events once the activity is destroyed
        disposable.dispose()
    }
}