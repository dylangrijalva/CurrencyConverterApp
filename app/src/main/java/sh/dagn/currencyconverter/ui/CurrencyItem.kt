package sh.dagn.currencyconverter.ui

data class CurrencyItem(
    val displayName: String,
    val flagResourceId: Int
) {
    override fun toString(): String {
        return displayName
    }
}
