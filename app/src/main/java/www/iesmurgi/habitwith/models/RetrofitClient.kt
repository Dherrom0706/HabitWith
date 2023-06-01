package www.iesmurgi.habitwith.models

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

object RetrofitClient {
    private const val BASE_URL = "https://wger.de/api/v2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}

interface ApiService {

    @GET
    suspend fun getExercises(@Url url: String): Response<List<Ejercicio>>

}