package sh.dagn.currencyconverter.data.cache.abstractions

import sh.dagn.currencyconverter.domain.models.Currency
import sh.dagn.currencyconverter.domain.models.CurrencyRates

interface CurrencyRatesCacheDataSource {
    suspend fun retrieve(currency: Currency): CurrencyRates?
    suspend fun save(currencyRates: CurrencyRates)
}
