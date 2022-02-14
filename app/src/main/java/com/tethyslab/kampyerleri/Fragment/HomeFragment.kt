package com.tethyslab.kampyerleri.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.tethyslab.kampyerleri.R
import com.tethyslab.kampyerleri.Recycler.RecyclerAdapter
import com.tethyslab.kampyerleri.Retrofit.ApiData
import com.tethyslab.kampyerleri.Retrofit.ApiSehir
import com.tethyslab.kampyerleri.Retrofit.ApiService
import com.tethyslab.kampyerleri.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    var totalItems : Int = 72
    var lastPage : Int = totalItems / 10
    var offset : Int = 0
    var currentPage : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()



    }
    fun getData(){
        RetrofitClient.getClient().create(ApiService::class.java)
            .getData("all", offset).enqueue(object : retrofit2.Callback<List<ApiData>>{

                override fun onResponse(
                    call: Call<List<ApiData>>,
                    response: Response<List<ApiData>>
                ) {
                    pageText.text = currentPage.toString() + " / " + (lastPage + 1).toString()
                    if(currentPage == (lastPage + 1)){
                        next.isEnabled = false
                    }else{
                        next.isEnabled = true
                    }
                    if(currentPage == 1){
                        prev.isEnabled = false
                    }else{
                        prev.isEnabled = true
                    }
                    val data = ArrayList(response.body()!!)
                    recyclerView.adapter= RecyclerAdapter(data)
                }

                override fun onFailure(call: Call<List<ApiData>>, t: Throwable) {
                    Log.d("as1","as2" + t)
                }
            })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prev?.setOnClickListener{
            offset -= 10
            currentPage -= 1
            getData()
        }
        next?.setOnClickListener{
            offset += 10
            currentPage += 1
            getData()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
