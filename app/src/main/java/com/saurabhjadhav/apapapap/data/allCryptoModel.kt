package com.saurabhjadhav.apapapap.data

import com.google.gson.annotations.SerializedName

data class allCryptoModel(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("baseAsset") val baseAsset: String,
    @SerializedName("quoteAsset") val quoteAsset: String,
    @SerializedName("openPrice") val openPrice: Double,
    @SerializedName("lowPrice") val lowPrice: Double,
    @SerializedName("highPrice") val highPrice: Double,
    @SerializedName("lastPrice") val lastPrice: Double,
    @SerializedName("volume") val volume: Double,
    @SerializedName("bidPrice") val bidPrice: Double,
    @SerializedName("askPrice") val askPrice: Double,
    @SerializedName("at") val at: Long
)