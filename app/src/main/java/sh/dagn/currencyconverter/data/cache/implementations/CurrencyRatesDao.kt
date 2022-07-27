package sh.dagn.currencyconverter.data.cache.implementations

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyRatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencyRatesModel: CurrencyRatesModel)

    @Query("SELECT * FROM currencyRates WHERE base = :base")
    suspend fun getByCurrencyBase(base: String): CurrencyRatesModel?
}
