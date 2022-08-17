package com.van.marsphotos.apis

import com.van.marsphotos.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val okhttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .also {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                it.addInterceptor(logging)
            }
        }.build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttpClient)
    .build()

interface MarsApiService {

    @GET("photos")
    suspend fun getPhotos(): List<MarsPhotosItem>
}

object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
