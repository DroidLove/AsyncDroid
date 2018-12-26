package inc.yoman.asyncdroid.dsl

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import kotlinx.coroutines.*

// CoroutineContext running on background threads.
internal val Background =
        newFixedThreadPoolContext(Runtime.getRuntime().availableProcessors() * 2, "Loader")

internal class CoroutineLifecycleListener(private val deferred: Deferred<*>) : LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun cancelCoroutine() {
    if (!deferred.isCancelled) {
      deferred.cancel()
    }
  }
}

fun <T> LifecycleOwner.load(loader: suspend () -> T): Deferred<T> {
  val deferred = GlobalScope.async(context = Background, start = CoroutineStart.LAZY, block = {
      loader()
  })

  lifecycle.addObserver(CoroutineLifecycleListener(deferred))
  return deferred
}

/**
 * Extension function on <code>Deferred<T><code> that creates a launches a coroutine which
 * will call <code>await()</code> and pass the returned value to <code>block()</code>.
 */
infix fun <T> Deferred<T>.then(block: suspend (T) -> Unit): Job {
  return GlobalScope.launch(context = Dispatchers.Main, block = {
      try {
          block(this@then.await())
      } catch (e: Exception) {
          // Just log the exception to confirm when we get cancelled (Expect JobCancellationException)
          Log.e("Inside DSL", "Exception in then()!")
          throw e
      }
  })
}

