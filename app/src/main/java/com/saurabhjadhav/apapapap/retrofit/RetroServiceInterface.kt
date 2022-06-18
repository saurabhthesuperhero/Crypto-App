package com.saurabhjadhav.apapapap.retrofit

import com.saurabhjadhav.apapapap.data.allCryptoModel
import com.saurabhjadhav.apapapap.data.individualCryptoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {
    @GET("tickers/24hr")
    fun getAllCrypto(): Call<ArrayList<allCryptoModel>>

    @GET("ticker/24hr")
    fun getIndividiualCrypto(@Query("symbol") symbol:String): Call<individualCryptoModel>
}