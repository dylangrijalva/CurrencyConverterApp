package sh.dagn.currencyconverter.data.cache.implementations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencyRates")
data class CurrencyRatesModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "base")
    val base: String,
    @ColumnInfo(name = "USD")
    val USD: Double,
    @ColumnInfo(name = "NIO")
    val NIO: Double,
    @ColumnInfo(name = "CAD")
    val CAD: Double,
    @ColumnInfo(name = "EUR")
    val EUR: Double
)
