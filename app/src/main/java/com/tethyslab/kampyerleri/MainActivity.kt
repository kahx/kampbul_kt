package com.tethyslab.kampyerleri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.tethyslab.kampyerleri.Fragment.FavFragment
import com.tethyslab.kampyerleri.Fragment.HomeFragment
import com.tethyslab.kampyerleri.Recycler.RecyclerAdapter
import com.tethyslab.kampyerleri.Retrofit.ApiData
import com.tethyslab.kampyerleri.Retrofit.ApiSehir
import com.tethyslab.kampyerleri.Retrofit.ApiService
import com.tethyslab.kampyerleri.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var favFragment: FavFragment
    val spinnerData : MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = ""
        RetrofitClient.getClient().create(ApiService::class.java)
            .getSehir("sehir").enqueue(object : retrofit2.Callback<List<ApiSehir>>{
                override fun onResponse(
                    call: Call<List<ApiSehir>>,
                    response: Response<List<ApiSehir>>
                ) {
                    spinnerData.add("tüm şehirler")
                    response.body()?.forEach {
                        spinnerData.add(it.sehir)
                        var arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerData)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinSehir?.adapter = arrayAdapter
                    }
                }
                override fun onFailure(call: Call<List<ApiSehir>>, t: Throwable) {
                    Log.d("Test", t.toString())
                }
            })

        spinSehir?.setOnItemSelectedListener( object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                RetrofitClient.getClient().create(ApiService::class.java)
                    .getData(spinSehir.selectedItem.toString(), 2).enqueue(object : retrofit2.Callback<List<ApiData>>{
                        override fun onResponse(
                            call: Call<List<ApiData>>,
                            response: Response<List<ApiData>>
                        ) {
                            var data = ArrayList(response.body()!!)



                        }

                        override fun onFailure(call: Call<List<ApiData>>, t: Throwable) {
                        }
                    })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        })

        val drawerToggle : ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout, toolBar, (R.string.open), (R.string.close)
            ){
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.fav -> {
                favFragment = FavFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, favFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
}
