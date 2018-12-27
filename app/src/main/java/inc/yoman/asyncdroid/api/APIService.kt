package inc.yoman.asyncdroid.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class APIService {

    private lateinit var apiInterface: APIInterface

    fun getClient(): APIInterface {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://limitless-lake-93364.herokuapp.com")
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        apiInterface = retrofit.create(APIInterface::class.java)

        return apiInterface
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(interceptor)
        return b.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    interface APIInterface {
        @GET("/hello")
        fun getEmployeeListing(): Deferred<Response<EmployeeListModel>>
    }
}
