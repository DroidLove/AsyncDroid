package inc.yoman.asyncdroid.fragment

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import com.google.gson.Gson
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.api.EmployeeModel
import kotlinx.android.synthetic.main.fragment_api_listing.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Jitesh on 26/03/18.
 */
class APICallAsyncFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { ApiCallTask(it, list_api, pro_bar).execute() }
    }

    class ApiCallTask(activity: FragmentActivity, list_api: ListView, pro_bar: ProgressBar) : AsyncTask<Unit, Void, String>() {
        var url = "https://limitless-lake-93364.herokuapp.com/hello"

        val list_api: ListView? = list_api
        val pro_bar: ProgressBar? = pro_bar
        val activity: FragmentActivity = activity
        var arrayList: ArrayList<String> = ArrayList()

        lateinit var client: OkHttpClient

        override fun doInBackground(vararg params: Unit?): String? {
            val request = Request.Builder()
                    .url(url)
                    .build()
            val response = client.newCall(request).execute()

            return response.body()?.string()
        }

        override fun onPreExecute() {
            super.onPreExecute()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client = OkHttpClient.Builder()
                    .addInterceptor(logging).build()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            var jsonObj = JSONObject(result)
            var jsonArray = JSONArray(jsonObj.get("employee").toString())


            for (i in 0 until jsonArray.length()) {
                var gsonObj: EmployeeModel = Gson().fromJson<EmployeeModel>(jsonArray?.get(i)?.toString(), EmployeeModel::class.java)
                arrayList.add(gsonObj.name)
            }

            pro_bar?.visibility = View.GONE
            val adapter = ArrayAdapter<String>(activity,
                    android.R.layout.simple_list_item_1, arrayList)
            list_api?.adapter = adapter

        }
    }

}