package sh.dagn.currencyconverter.domain

import sh.dagn.currencyconverter.data.cache.abstractions.CurrencyRatesCacheDataSource
import sh.dagn.currencyconverter.data.remote.abstractions.CurrencyRatesRemoteDataSource
import sh.dagn.currencyconverter.domain.models.Currency
import sh.dagn.currencyconverter.domain.models.CurrencyRates
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.math.round

class DefaultCurrencyConverter @Inject constructor(
    private val cacheDataSource: CurrencyRatesCacheDataSource,
    private val remoteDataSource: CurrencyRatesRemoteDataSource
) : CurrencyConverter {
    override suspend fun convert(
        value: Double,
        fromCurrency: Currency,
        toCurrency: Currency
    ): Result<Double> {
        val currencyRates = getCurrencyRates(fromCurrency)
            ?: return Result.Error("Could not able to retrieve currency rates for the specific currency")

        val toCurrencyRate = getRateForCurrency(toCurrency, currencyRates)
        val convertedCurrencyValue =
            round(value.absoluteValue * toCurrencyRate * 100) / 100

        return Result.Success(convertedCurrencyValue)
    }

    private suspend fun getCurrencyRates(currency: Currency): CurrencyRates? {
        val currencyRates = cacheDataSource.retrieve(currency)
        if (currencyRates == null) {
            val newestCurrencyRates = remoteDataSource.retrieve(currency) ?: return null
            cacheDataSource.save(newestCurrencyRates)
            return newestCurrencyRates
        }

        return currencyRates
    }

    private fun getRateForCurrency(currency: Currency, currencyRates: CurrencyRates): Double {
        return when (currency) {
            Currency.USD -> currencyRates.USD
            Currency.NIO -> currencyRates.NIO
            Currency.CAD -> currencyRates.CAD
            Currency.EUR -> currencyRates.EUR
        }
    }
}
