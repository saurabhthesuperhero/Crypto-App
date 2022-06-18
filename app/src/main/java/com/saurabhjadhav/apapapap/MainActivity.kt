package com.saurabhjadhav.apapapap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.saurabhjadhav.apapapap.adapter.RecyclerviewAdapter
import com.saurabhjadhav.apapapap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        iniRefreshListener()
    }


    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = RecyclerviewAdapter()
        binding.recyclerview.adapter = recyclerviewAdapter

    }


    fun iniRefreshListener() {
        val swipeRefreshLayout=findViewById<SwipeRefreshLayout>(R.id.swipe_layout)
        swipeRefreshLayout.setOnRefreshListener { // This method gets called when user pull for refresh,
            initViewModel()
            val handler = Handler()
            handler.postDelayed({
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false)
                }
            }, 3000)
        }
    }
    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllCryptoObserver().observe(this) {
            if (it != null) {
                recyclerviewAdapter.tempList = it
                recyclerviewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.makeAllCryptoApiCall()
    }
}