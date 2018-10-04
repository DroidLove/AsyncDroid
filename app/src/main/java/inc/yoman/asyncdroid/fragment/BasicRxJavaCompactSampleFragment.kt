package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.yoman.asyncdroid.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_sub_sample.*

/**
 * Basic Observable, Observer, Subscriber example
 * Observable emits list of animal names
 */
class BasicRxJavaCompactSampleFragment : Fragment() {

    private var TAG = "BasicRxJavaCompactSampleFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        callRxJava()

        button_retry.setOnClickListener { callRxJava() }
    }

    private fun callRxJava() {
        var stringResult : String =""

        // observable
        val animalsObservable = getAnimalsObservable()

        // observer
//        val animalsObserver = getAnimalsObserver()

        // observer subscribing to observable
        // inline subscriber that implements OnNext() and OnError() handlers
        animalsObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe( {result ->
                    Log.d(TAG, "Name : $result")
                    stringResult += "$result "
                    textView_result.text = stringResult
                },{ e->})
    }

    private fun getAnimalsObservable(): Observable<String> {
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox")
    }

//    private fun getAnimalsObserver(): Observer<String> {
//        return object : Observer<String> {
//            override fun onSubscribe(d: Disposable) {
//                Log.d(TAG, "onSubscribe")
//            }
//
//            override fun onNext(s: String) {
//                Log.d(TAG, "Name: $s")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.e(TAG, "onError: " + e.message)
//            }
//
//            override fun onComplete() {
//                Log.d(TAG, "All items are emitted!")
//            }
//        }
//    }
}