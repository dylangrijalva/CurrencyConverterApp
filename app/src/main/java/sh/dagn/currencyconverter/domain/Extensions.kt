package sh.dagn.currencyconverter.domain

import sh.dagn.currencyconverter.domain.models.Currency

fun String.toCurrency(): Currency {
    return Currency.values().firstOrNull {
        it.toString() == this
    } ?: Currency.USD
}
