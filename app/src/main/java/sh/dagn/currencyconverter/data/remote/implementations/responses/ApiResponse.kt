package sh.dagn.currencyconverter.data.remote.implementations.responses

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: CurrencyRatesResponse
)
