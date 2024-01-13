package com.jy02.newsapp.module

import com.jy02.newsapp.data.network.NewsService
import com.jy02.newsapp.util.Constants.Companion.NEWS_BASEURL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    private fun moshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun okHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    fun providesRetforit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .baseUrl(NEWS_BASEURL)
            .client(okHttpClient())
            .build()
    }

    @Provides
    fun providesNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}