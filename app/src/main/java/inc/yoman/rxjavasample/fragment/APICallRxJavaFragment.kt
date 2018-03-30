package inc.yoman.rxjavasample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.Gson
import inc.yoman.rxjavasample.R
import inc.yoman.rxjavasample.api.EmployeeModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_api_listing.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject

class APICallRxJavaFragment : Fragment() {
    var url = "https://limitless-lake-93364.herokuapp.com/hello"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiCall()
    }

    private fun apiCall() {
        Flowable.fromCallable {
            val request = Request.Builder()
                    .url(url)
                    .build()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            var client = OkHttpClient.Builder()
                    .addInterceptor(logging).build()

            val response = client.newCall(request).execute()
            return@fromCallable response?.body()?.string()
        }
                .subscribeOn(Schedulers.io())
                .map { result -> mappingResult(result) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    pro_bar.visibility = View.GONE
                    activity?.let {
                        val adapter = ArrayAdapter<String>(it,
                                android.R.layout.simple_list_item_1, result)
                        list_api.adapter = adapter
                    }

                }, { e ->
                })
    }

    private fun mappingResult(result: Any): ArrayList<String> {
        var listEmployee: ArrayList<String> = ArrayList()
        var jsonObj = JSONObject(result.toString())
        var jsonArray = JSONArray(jsonObj.get("employee").toString())

        Flowable
                .range(0, jsonArray.length())
                .flatMap { idx -> getEmployeeObservable(idx, jsonArray) }
                .subscribe({ result ->
                    listEmployee.add(result.name)
                }
                        , { e -> Log.e("Response Mapping error", e.toString()) })

        return listEmployee
    }

    private fun getEmployeeObservable(idx: Int, jsonArray: JSONArray?): Flowable<EmployeeModel> {
        var gsonObj: EmployeeModel = Gson().fromJson<EmployeeModel>(jsonArray?.get(idx)?.toString(), EmployeeModel::class.java)
        return Flowable.just(gsonObj)
    }

}