package com.tethyslab.kampyerleri.Retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api2.php")
    fun getData(@Query("param") param : String, @Query("offset") offset : Int): Call<List<ApiData>>

    @GET("api2.php")
    fun getSehir(@Query("sehir") sehir : String) : Call<List<ApiSehir>>

}