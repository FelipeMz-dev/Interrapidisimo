package com.felipemz.interrapidsimo.di

import com.felipemz.interrapidsimo.data.api.VersionApi
import com.felipemz.interrapidsimo.data.usecase.ValidateVersionUseCaseImpl
import com.felipemz.interrapidsimo.domain.usecase.ValidateVersionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apitesting.interrapidisimo.co/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideVersionApi(retrofit: Retrofit): VersionApi {
        return retrofit.create(VersionApi::class.java)
    }

    @Provides
    fun provideValidateVersionUseCase(api: VersionApi): ValidateVersionUseCase {
        return ValidateVersionUseCaseImpl(api)
    }
}