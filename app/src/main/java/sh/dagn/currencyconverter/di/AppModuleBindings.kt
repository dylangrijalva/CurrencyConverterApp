package sh.dagn.currencyconverter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sh.dagn.currencyconverter.data.cache.abstractions.CurrencyRatesCacheDataSource
import sh.dagn.currencyconverter.data.cache.implementations.RoomCurrencyRatesCacheDataSource
import sh.dagn.currencyconverter.data.remote.abstractions.CurrencyRatesRemoteDataSource
import sh.dagn.currencyconverter.data.remote.implementations.ApiCurrencyRatesRemoteDataSource
import sh.dagn.currencyconverter.domain.CurrencyConverter
import sh.dagn.currencyconverter.domain.DefaultCurrencyConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBindings {
    @Binds
    @Singleton
    abstract fun bindCurrencyRatesCacheDataSource(implementation: RoomCurrencyRatesCacheDataSource): CurrencyRatesCacheDataSource

    @Binds
    @Singleton
    abstract fun bindCurrencyRatesRemoteDataSource(implementation: ApiCurrencyRatesRemoteDataSource): CurrencyRatesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCurrencyConverter(implementation: DefaultCurrencyConverter): CurrencyConverter
}
