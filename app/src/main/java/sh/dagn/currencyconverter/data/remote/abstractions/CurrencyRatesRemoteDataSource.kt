package sh.dagn.currencyconverter.data.remote.abstractions

import sh.dagn.currencyconverter.domain.models.Currency
import sh.dagn.currencyconverter.domain.models.CurrencyRates

interface CurrencyRatesRemoteDataSource {
    suspend fun retrieve(currency: Currency): CurrencyRates?
}
