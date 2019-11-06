package com.stechlabs.ApiBuilder

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {

    private const val Base_Url="http://192.168.1.103:8000/"

    var retrofitBuilder=
        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var apibuilder:MyApiBuilder=
        retrofitBuilder.create(MyApiBuilder::class.java)

}

