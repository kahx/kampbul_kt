package com.tethyslab.kampyerleri.Recycler

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tethyslab.kampyerleri.R
import com.tethyslab.kampyerleri.Retrofit.ApiData

class RecyclerViewHolder(viewGroup : ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(
    R.layout.api_item, viewGroup, false)) {

    private val apiImg by lazy {itemView.findViewById<ImageView>(R.id.apiImg)}
    private val apiBaslik by lazy {itemView.findViewById<TextView>(R.id.apiBaslik)}
    private val apiAciklama by lazy {itemView.findViewById<TextView>(R.id.apiAciklama)}
    private val apiAdres by lazy {itemView.findViewById<TextView>(R.id.apiAdres)}

    fun bindTo(dto : ApiData){
        apiBaslik.text = dto.baslik
        apiAciklama.text = dto.aciklama
        apiAdres.text = "Adres: "+dto.adres
        Picasso.get().load(dto.foto).into(apiImg)
        apiImg.layoutParams.height = (Resources.getSystem().displayMetrics.heightPixels) / 3
        apiImg.requestLayout()
    }
}