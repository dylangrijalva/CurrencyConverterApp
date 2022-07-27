package sh.dagn.currencyconverter.domain

import sh.dagn.currencyconverter.domain.models.Currency

interface CurrencyConverter {
    suspend fun convert(value: Double, fromCurrency: Currency, toCurrency: Currency): Result<Double>
}
