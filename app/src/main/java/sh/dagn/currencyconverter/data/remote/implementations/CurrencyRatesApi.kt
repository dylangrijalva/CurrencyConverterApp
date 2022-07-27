package sh.dagn.currencyconverter.data.remote.implementations

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import sh.dagn.currencyconverter.data.remote.implementations.responses.ApiResponse

interface CurrencyRatesApi {
    @GET("latest")
    suspend fun getCurrencyRates(
        @Query("base") base: String,
        @Header("apikey") apiKey: String,
        @Query("symbols") symbols: String,
    ): ApiResponse
}
