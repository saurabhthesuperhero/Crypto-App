package com.saurabhjadhav.apapapap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saurabhjadhav.apapapap.data.allCryptoModel
import com.saurabhjadhav.apapapap.data.individualCryptoModel
import com.saurabhjadhav.apapapap.retrofit.RetroInstance
import com.saurabhjadhav.apapapap.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var livedataAllCrypto: MutableLiveData<ArrayList<allCryptoModel>> = MutableLiveData()

    fun getAllCryptoObserver(): LiveData<ArrayList<allCryptoModel>> {
        return livedataAllCrypto
    }

    var livedataSingleCrypto: MutableLiveData<individualCryptoModel> = MutableLiveData()

    fun getSingleCryptoObserver(): LiveData<individualCryptoModel> {
        return livedataSingleCrypto
    }

    fun makeAllCryptoApiCall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getAllCrypto()
        call.enqueue(object :
            retrofit2.Callback<ArrayList<allCryptoModel>> {
            override fun onResponse(
                call: Call<ArrayList<allCryptoModel>>,
                response: Response<ArrayList<allCryptoModel>>
            ) {
                Log.e("checkData", "onResponse: " + response.body())
                livedataAllCrypto.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<allCryptoModel>>, t: Throwable) {
                Log.e("checkData", "onFailure: $t")
                livedataAllCrypto.postValue(null)
            }


        })
    }

    fun makeSingleCryptoApiCall(symbol: String) {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getIndividiualCrypto(symbol)
        call.enqueue(object :
            retrofit2.Callback<individualCryptoModel> {
            override fun onResponse(
                call: Call<individualCryptoModel>,
                response: Response<individualCryptoModel>
            ) {
                Log.e("checkData", "onResponse: " + response.body())
                livedataSingleCrypto.postValue(response.body())
            }

            override fun onFailure(call: Call<individualCryptoModel>, t: Throwable) {
                Log.e("checkData", "onFailure: $t")
                livedataSingleCrypto.postValue(null)
            }


        })
    }
}