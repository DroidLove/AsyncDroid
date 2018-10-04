package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.Gson
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.api.EmployeeModel
import inc.yoman.asyncdroid.dsl.load
import inc.yoman.asyncdroid.dsl.then
import kotlinx.android.synthetic.main.fragment_api_listing.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject

class APICallCoroutinesWithDslFragment : Fragment() {

    var url = "https://limitless-lake-93364.herokuapp.com/hello"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myCoroutine()
    }

    fun myCoroutine() {
        load {
            val request = Request.Builder()
                    .url(url)
                    .build()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            var client = OkHttpClient.Builder()
                    .addInterceptor(logging).build()

            client.newCall(request).execute()
        } then {
            handlingUI(it?.body()?.string())
        }

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
}