package inc.yoman.asyncdroid.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import inc.yoman.asyncdroid.R
import inc.yoman.asyncdroid.api.APIService
import inc.yoman.asyncdroid.api.EmployeeListModel
import inc.yoman.asyncdroid.api.EmployeeModel
import kotlinx.android.synthetic.main.fragment_api_listing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class APICallRetrofitCoroutinesFragment : Fragment() {

    internal lateinit var retroApiInterface: APIService.APIInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retroApiInterface = APIService().getClient()

        myCoroutineRetrofit()
    }

    private fun myCoroutineRetrofit() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val request = retroApiInterface.getEmployeeListing()
                val response = request.await().body()
                handlingUI(response)
            } catch (exception: Exception) {
                Log.e("Retrofit Exception", exception.toString())
            }
        }
    }

    private fun handlingUI(response: EmployeeListModel?) {
        GlobalScope.launch(Dispatchers.Main) {
            val resultList = mappingResult(response?.employee)

            pro_bar.visibility = View.GONE
            activity?.let {
                val adapter = ArrayAdapter<String>(it,
                        android.R.layout.simple_list_item_1, resultList)
                list_api.adapter = adapter
            }
        }
    }

    private fun mappingResult(result: List<EmployeeModel>?): ArrayList<String> {
        var listEmployee: ArrayList<String> = ArrayList()

        result?.let {
            for (i in 0 until it.size) {
                listEmployee.add(it[i].name)
            }
        }

        return listEmployee
    }
}