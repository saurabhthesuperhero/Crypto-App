package com.saurabhjadhav.apapapap.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.saurabhjadhav.apapapap.R
import com.saurabhjadhav.apapapap.data.allCryptoModel
import com.saurabhjadhav.apapapap.databinding.ItemCryptoBinding
import com.saurabhjadhav.apapapap.detailActivity

class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {
    private lateinit var binding: ItemCryptoBinding
    private lateinit var context: Context
    private var cryptoList: ArrayList<allCryptoModel>? = null
        set(value) {
            field = value
            tempList = value
        }

    var tempList: ArrayList<allCryptoModel>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerviewAdapter.MyViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_crypto,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerviewAdapter.MyViewHolder, position: Int) {
        tempList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = if (tempList == null) 0 else tempList?.size!!


    inner class MyViewHolder(private val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(allCryptoModel: allCryptoModel) {
            binding.symbol.text = allCryptoModel.baseAsset
            Log.d("checkme", "bind: " + allCryptoModel.symbol)
            binding.price.text =
                allCryptoModel.openPrice.toString() + " " + allCryptoModel.quoteAsset
            binding.root.setOnClickListener {
                val intent = Intent(context, detailActivity::class.java)
                intent.putExtra("symbol", allCryptoModel.symbol)
                context.startActivity(intent)
            }
        }
    }
}