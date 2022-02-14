package com.tethyslab.kampyerleri.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{

        fun getClient(): Retrofit{
            return Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

    }
}
