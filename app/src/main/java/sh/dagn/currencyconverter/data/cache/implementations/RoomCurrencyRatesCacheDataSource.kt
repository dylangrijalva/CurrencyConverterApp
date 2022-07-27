package sh.dagn.currencyconverter.data.cache.implementations

import sh.dagn.currencyconverter.data.cache.abstractions.CurrencyRatesCacheDataSource
import sh.dagn.currencyconverter.domain.models.Currency
import sh.dagn.currencyconverter.domain.models.CurrencyRates
import javax.inject.Inject

class RoomCurrencyRatesCacheDataSource @Inject constructor(
    database: AppDatabase
) : CurrencyRatesCacheDataSource {
    private val dao = database.currencyRatesDao()
    override suspend fun retrieve(currency: Currency): CurrencyRates? {
        val currencyRates = dao.getByCurrencyBase(currency.toString())
        if (currencyRates != null) {
            return CurrencyRates(
                currencyRates.base,
                currencyRates.USD,
                currencyRates.NIO,
                currencyRates.CAD,
                currencyRates.EUR,
            )
        }

        return null
    }

    override suspend fun save(currencyRates: CurrencyRates) {
        dao.insert(CurrencyRatesModel(
            currencyRates.Base,
            currencyRates.USD,
            currencyRates.NIO,
            currencyRates.CAD,
            currencyRates.EUR,
        ))
    }
}
