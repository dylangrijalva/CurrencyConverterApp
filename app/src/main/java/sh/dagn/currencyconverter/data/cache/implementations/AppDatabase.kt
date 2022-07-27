package sh.dagn.currencyconverter.data.cache.implementations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    exportSchema = false,
    entities = [CurrencyRatesModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyRatesDao(): CurrencyRatesDao

    companion object {
        private const val DB_NAME = "currency_rates.db"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
