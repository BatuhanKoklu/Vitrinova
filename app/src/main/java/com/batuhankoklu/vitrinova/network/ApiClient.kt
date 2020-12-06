package com.batuhankoklu.vitrinova.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null
    private val base_url = "https://www.vitrinova.com/api/v2/"


    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

        return retrofit as Retrofit
    }

}