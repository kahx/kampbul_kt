package com.tethyslab.kampyerleri.Retrofit

data class ApiData (
    val baslik : String,
    val aciklama : String,
    val id : Int,
    val foto : String,
    val adres : String,
    val sehir : String,
    val bolge : String
    )
data class ApiSehir (
    val sehir : String
)