package com.fortytwo.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fortytwo.R
import com.fortytwo.network.ApiHelper
import com.fortytwo.network.RetrofitBuilder
import com.fortytwo.pref.PreferencesHelper
import com.fortytwo.pref.PreferencesHelperImpl
import com.fortytwo.ui.base.ViewModelFactory
import com.fortytwo.utils.NetworkHelper
import com.fortytwo.utils.Status
import com.fortytwo.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var  adapter : HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        fetchBreeds()
    }

    private fun init() {
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService), NetworkHelper(this), PreferencesHelperImpl(this)
            )
        ).get(HomeViewModel::class.java)
    }

    private fun fetchBreeds() {
        homeViewModel.fetchBreeds().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerBreeds.layoutManager = LinearLayoutManager(this)
                        adapter = HomeAdapter(this, it.data!!)
                        recyclerBreeds.adapter = adapter
                        progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Snackbar.make(rootLayout, "" + it.message, Snackbar.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}
