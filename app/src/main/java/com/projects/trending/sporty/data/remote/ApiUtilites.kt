package com.projects.trending.sporty.data.remote

import com.projects.trending.sporty.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilites {


  private val retrofit by lazy {
      // Defining the logging interceptors for debugging
      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)

      // adding the interceptor in the client
      val client = OkHttpClient.Builder()
          .addInterceptor(logging)
          .build()

      // Creating Retrofit Instance and return it
      Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .client(client)
          .build()
  }

    val service by lazy {
        retrofit.create(ApiInterface :: class.java)
    }


}