package com.example.crytocurrencyapp.di

import com.example.crytocurrencyapp.common.Constants
import com.example.crytocurrencyapp.data.remote.CoinPeprikaApi
import com.example.crytocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.crytocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// InstallIn gives how long our dependencies live
// And
// Installing in SingletonComponent means dependencies will live as long as our app does
// and these are all singleton
object AppModule {

    // Provides = out fun provides a dependencies
    @Provides
    // Singleton = it ensures that there exists only a single instance of whatever the fun returns
    // OR
    // It makes sure that there is only a single instance throughout the whole lifetime of our app
    @Singleton
    fun providesPaprikaApi(): CoinPeprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPeprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPeprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}


















