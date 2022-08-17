package com.van.marsphotos.apis

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://srv0api-dev-v2-dot-framy-stage.uc.r.appspot.com/"

private const val OKHTTP_TIMEOUT = 5L

private val okhttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
        .also {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                it.addInterceptor(logging)
            }
        }.build()

private val retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

interface PlayseeApiService {

    @GET("test1.0/backstage/exm1")
    suspend fun getVideos(
        @Header("authorization") authorization: String
    ): Response<PlayseeResp>
}

object PlayseeApi {
    val retrofitService: PlayseeApiService by lazy { retrofit.create(PlayseeApiService::class.java) }
}