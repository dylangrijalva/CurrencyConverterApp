package sh.dagn.currencyconverter.data.remote.implementations

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import sh.dagn.currencyconverter.R
import sh.dagn.currencyconverter.data.remote.abstractions.CurrencyRatesRemoteDataSource
import sh.dagn.currencyconverter.domain.models.Currency
import sh.dagn.currencyconverter.domain.models.CurrencyRates
import javax.inject.Inject

class ApiCurrencyRatesRemoteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val currencyRatesApi: CurrencyRatesApi
) : CurrencyRatesRemoteDataSource {
    override suspend fun retrieve(currency: Currency): CurrencyRates? {
        return try {
            val apiKey = context.getString(R.string.api_key)
            val response =
                currencyRatesApi.getCurrencyRates(
                    currency.toString(),
                    apiKey,
                    "USD,NIO,CAD,EUR"
                )
            CurrencyRates(
                response.base,
                response.rates.USD,
                response.rates.NIO,
                response.rates.CAD,
                response.rates.EUR,
            )
        } catch (exception: Exception) {
            null
        }
    }

    private fun buildSymbolsQueryParam(): String {
        val queryParam = Currency.values().fold("") { acc, currency ->
            return "$acc,$currency"
        }

        return if (queryParam.startsWith(",")) queryParam.substring(1) else queryParam
    }
}
