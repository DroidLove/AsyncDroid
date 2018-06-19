# RxJavaSample
Introduction to Reactive Programming - RxJava and RxAndroid with samples plus comparison with imperative procedural programming.

<p align="center"><img align="center" src="https://github.com/DroidLove/RxJavaSample/blob/master/screenshots/screenshot_01.png" alt="Alt text"></p>

## Basics
* [BasicRxJavaSampleFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/BasicRxJavaSampleFragment.kt) - Basic `Observable`, `Observer` and Subscription example. Emitting list of animal names.
* [RxJavaDisposableSampleFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/RxJavaDisposableSampleFragment.kt) - Introduced Disposable to dispose the subscription.
* [RxJavaOperatorSampleFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/RxJavaOperatorSampleFragment.kt) - Introducing `filter()` operator to filter out the animal names starting with letter `b`.
* [RxJavaCompositeSampleFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/RxJavaCompositeSampleFragment.kt) - Introduced `CompositeDisposable` and `DisposableObserver`. Also Example of chaining of operators. `map()` and `filter()` operators are used together.
* [BasicRxJavaCompactSampleFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/BasicRxJavaCompactSampleFragment.kt) - Basic `Observable`, `Observer` with inline subscriber that implements OnNext() and OnError() handlers

## Network Calls
* [APICallAsyncFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/APICallAsyncFragment.kt) - Using AsyncTask, making a network call (Just for comparison)
* [APICallRxJavaFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/APICallRxJavaFragment.kt) - Making similar network call using RxJava and iterating over list using `flatMap()` 

## Bonus
* [APICallCoroutinesFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/APICallCoroutinesFragment.kt) - Making similar network call using `Coroutines` (Again for comparison)
* [APICallHelperCoroutinesFragment](https://github.com/DroidLove/RxJavaSample/blob/master/app/src/main/java/inc/yoman/rxjavasample/fragment/APICallHelperCoroutinesFragment.kt) - Making similar network call using `Coroutines` with helper methods (Again for comparison)

## Acknowledgments

* [Androidhive](https://www.androidhive.info/RxJava/) - Code samples and exaplanation
