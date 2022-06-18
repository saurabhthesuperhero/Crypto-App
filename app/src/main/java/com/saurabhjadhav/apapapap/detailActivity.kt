package com.saurabhjadhav.apapapap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.saurabhjadhav.apapapap.databinding.ActivityDetailBinding

class detailActivity : AppCompatActivity() {
    private lateinit var symbol:String
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.extras != null) {
            symbol = intent.extras!!.getString("symbol").toString()
        }
        initViewModel()

    }
    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getSingleCryptoObserver().observe(this) {
            if (it != null) {
               //todo
                Log.d("checkme", "initViewModel: $it")
                binding.Symbol.text=it.baseAsset
                binding.highPrice.text=it.highPrice.toString()
                binding.lowPrice.text=it.lowPrice.toString()
                binding.volume.text=it.volume.toString()
                binding.openPrice.text=it.openPrice.toString()
            } else {
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.makeSingleCryptoApiCall(symbol)
    }
}