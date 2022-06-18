package com.saurabhjadhav.apapapap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
    }


    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = RecyclerviewAdapter()
        binding.recyclerview.adapter = recyclerviewAdapter

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