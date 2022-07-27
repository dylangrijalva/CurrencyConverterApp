package sh.dagn.currencyconverter.data.remote.implementations.responses

import com.google.gson.annotations.SerializedName

data class CurrencyRatesResponse(
    @SerializedName("USD")
    val USD: Double,
    @SerializedName("NIO")
    val NIO: Double,
    @SerializedName("CAD")
    val CAD: Double,
    @SerializedName("EUR")
    val EUR: Double
)
