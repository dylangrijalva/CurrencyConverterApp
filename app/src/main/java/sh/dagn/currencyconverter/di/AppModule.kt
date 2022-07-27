package sh.dagn.currencyconverter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sh.dagn.currencyconverter.R
import sh.dagn.currencyconverter.data.cache.implementations.AppDatabase
import sh.dagn.currencyconverter.data.remote.implementations.CurrencyRatesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCurrencyRatesApi(@ApplicationContext context: Context): CurrencyRatesApi {
        val baseApiUrl = context.getString(R.string.api_url)

        return Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyRatesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.create(context)
    }
}
