package inc.yoman.rxjavasample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import inc.yoman.rxjavasample.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sub_sample.*

/**
 * Basic Observable, Observer, Subscriber example
 * Observable emits list of animal names
 * You can see Disposable introduced in this example
 */
class RxJavaCompositeSampleFragment : Fragment() {

    private var TAG = "RxJavaCompositeSampleFragment"

    private val compositeDisposable = CompositeDisposable()
    var nameString : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callRxJava()

        button_retry.setOnClickListener { callRxJava() }
    }

    private fun callRxJava() {
        val animalsObservable = getAnimalsObservable()

        val animalsObserver = getAnimalsObserver()

        val animalsObserverAllCaps = getAnimalsAllCapsObserver()

        /**
         * filter() is used to filter out the animal names starting with `b`
         * */
        compositeDisposable.add(
                animalsObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter { s -> s.toLowerCase().startsWith("b") }
                        .subscribeWith<DisposableObserver<String>>(animalsObserver))

        /**
         * filter() is used to filter out the animal names starting with 'c'
         * map() is used to transform all the characters to UPPER case
         * */
        compositeDisposable.add(
                animalsObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter { s -> s.toLowerCase().startsWith("c") }
                        .map { s -> s.toUpperCase() }
                        .subscribeWith(animalsObserverAllCaps))
    }

    private fun getAnimalsObserver(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {

            override fun onNext(s: String) {
                Log.d(TAG, "Name: $s")
                nameString += "$s "
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "All items are emitted!")
                Toast.makeText(activity,nameString,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getAnimalsAllCapsObserver(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {


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
        return Observable.fromArray(
                "Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog")
    }

    override fun onDestroy() {
        super.onDestroy()

        // don't send events once the activity is destroyed
        compositeDisposable.clear()
    }
}