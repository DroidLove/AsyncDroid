package inc.yoman.asyncdroid.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class APIService {

    lateinit var apiInterface: APIInterface

    fun getClient(): APIInterface {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://limitless-lake-93364.herokuapp.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiInterface = retrofit.create(APIInterface::class.java)

        return apiInterface
    }

    interface APIInterface {
        @GET("/hello")
        fun getEmployeeListing(): Deferred<Response<EmployeeModel>>
    }
}
