package com.app.kodex

import com.app.kodex.common.CONSTANT
import com.app.kodex.data.RepositoryImpl
import com.app.kodex.domain.KodexRepository
import com.app.kodex.domain.kodexApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideKodexApi() : kodexApi {
        return Retrofit.Builder()
            .baseUrl(CONSTANT.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .build()
            .create(kodexApi::class.java)
    }


    @Provides
    @Singleton
    fun provideKodexRepository(api : kodexApi) : KodexRepository {
        return RepositoryImpl(api)
    }

}