package com.app.kodex.chat

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
object ChatModule {
    @Provides
    @Singleton
    fun provideChatApi() : ChatApi {
        return Retrofit.Builder()
            .baseUrl(CONSTANT.BASE_URL_FLASK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .build()
            .create(ChatApi::class.java)
    }


    @Provides
    @Singleton
    fun provideKodexRepository(api : ChatApi) : ChatRepository {
        return chatRepositoryImpl(api)
    }

}