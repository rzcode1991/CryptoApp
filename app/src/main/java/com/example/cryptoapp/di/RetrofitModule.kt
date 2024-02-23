package com.example.cryptoapp.di

import com.example.cryptoapp.model.network.ApiInterface
import com.example.cryptoapp.utils.Constants.API_KEY
import com.example.cryptoapp.utils.Constants.DETAILED_MAIN_URL
import com.example.cryptoapp.utils.Constants.LIST_MAIN_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    @Named("listBaseUrl")
    fun provideListRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(LIST_MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    @Named("detailsBaseUrl")
    fun provideDetailsRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(DETAILED_MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    @Provides
    @Singleton
    @Named("listApiInterface")
    fun provideListApiInterface(@Named("listBaseUrl") retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)


    @Provides
    @Singleton
    @Named("detailsApiInterface")
    fun provideDetailsApiInterface(@Named("detailsBaseUrl") retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)


    @Provides
    @Singleton
    fun provideApiKey(): String = API_KEY


}