package sh.dagn.currencyconverter.domain.models

data class CurrencyRates(
    val Base: String,
    val USD: Double,
    val NIO: Double,
    val CAD: Double,
    val EUR: Double
)
