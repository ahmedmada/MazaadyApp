package com.ahmed.mazaadyapp.di

import com.ahmed.mazaadyapp.common.Constants
import com.ahmed.mazaadyapp.data.remote.RetroService
import com.ahmed.mazaadyapp.data.remote.repository.RetroImpl
import com.ahmed.mazaadyapp.domain.repository.RetroRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        // set your desired log level
// set your desired log level

// add your other interceptors …

// add logging as last interceptor
// add your other interceptors …

// add logging as last interceptor
        // <-- this is the important line!
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .callTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun getRestApiService(retrofit: Retrofit): RetroService {
        return retrofit.create(RetroService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepo(retroService: RetroService): RetroRepo {
        return RetroImpl(retroService)
    }

}