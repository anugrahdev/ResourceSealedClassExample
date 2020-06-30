package com.anugrahdev.resourcesealedclassexample.network

import com.anugrahdev.resourcesealedclassexample.models.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String="id",
        @Query("category") category:String = "sports",
        @Query("pageSize") pageSize:Int = 100,
        @Query("apiKey") apiKey:String ="5829064962644ff794744f06d57c7607"
    ): Response<NewsResponse>

    companion object {
        operator fun invoke() : ApiService{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)


        }
    }

}