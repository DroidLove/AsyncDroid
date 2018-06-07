package inc.yoman.rxjavasample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.Gson
import inc.yoman.rxjavasample.R
import inc.yoman.rxjavasample.api.EmployeeModel
import kotlinx.android.synthetic.main.fragment_api_listing.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject

class APICallCoroutinesFragment : Fragment() {

    var url = "https://limitless-lake-93364.herokuapp.com/hello"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { myCoroutine() }
    }

    fun myCoroutine() {
        launch(UI) {
            val result = async(CommonPool) {

                val request = Request.Builder()
                        .url(url)
                        .build()

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                var client = OkHttpClient.Builder()
                        .addInterceptor(logging).build()

                client.newCall(request).execute()

            }.await()

            handlingUI(result?.body()?.string())
        }
    }

    private fun mappingResult(result: String?): ArrayList<String> {
        var listEmployee: ArrayList<String> = ArrayList()
        var jsonObj = JSONObject(result)
        var jsonArray = JSONArray(jsonObj.get("employee").toString())

        for (i in 0 until jsonArray.length()) {
            val gsonObj = Gson().fromJson<EmployeeModel>(jsonArray.get(i)?.toString(), EmployeeModel::class.java)

            listEmployee.add(gsonObj.name)
        }

        return listEmployee
    }

    private fun handlingUI(result: String?) {
        val resultList = mappingResult(result)

        pro_bar.visibility = View.GONE
        activity?.let {
            val adapter = ArrayAdapter<String>(it,
                    android.R.layout.simple_list_item_1, resultList)
            list_api.adapter = adapter
        }
    }
}