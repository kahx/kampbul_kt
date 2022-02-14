package com.tethyslab.kampyerleri.Recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tethyslab.kampyerleri.Retrofit.ApiData

class RecyclerAdapter(dataList : List<ApiData>) : RecyclerView.Adapter<RecyclerViewHolder>() {

    var dataList = dataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindTo(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(dataList : List<ApiData>){
        this.dataList = dataList
    }

}