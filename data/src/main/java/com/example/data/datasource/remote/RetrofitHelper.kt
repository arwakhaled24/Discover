package com.example.data.datasource.remote

/*
import android.util.Log
import com.example.data.utils.NetworkConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper{

    val logging = HttpLoggingInterceptor(){ message ->
        Log.i("TAG", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: Services by lazy {
        createRetrofit(NetworkConstants.BASE_URL).create(Services::class.java)
    }
}*/
