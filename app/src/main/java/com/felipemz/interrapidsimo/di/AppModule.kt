package com.felipemz.interrapidsimo.di

import android.content.Context
import androidx.room.Room
import com.felipemz.interrapidsimo.data.api.LoginApi
import com.felipemz.interrapidsimo.data.api.VersionApi
import com.felipemz.interrapidsimo.data.db.AppDatabase
import com.felipemz.interrapidsimo.data.db.dao.UserDao
import com.felipemz.interrapidsimo.data.repository.UserRepositoryImpl
import com.felipemz.interrapidsimo.data.usecase.GetUserAccountUseCaseImpl
import com.felipemz.interrapidsimo.data.usecase.LoginUseCaseImpl
import com.felipemz.interrapidsimo.data.usecase.ValidateVersionUseCaseImpl
import com.felipemz.interrapidsimo.domain.repository.UserRepository
import com.felipemz.interrapidsimo.domain.usecase.GetUserAccountUseCase
import com.felipemz.interrapidsimo.domain.usecase.LoginUseCase
import com.felipemz.interrapidsimo.domain.usecase.ValidateVersionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
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

    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    fun provideLoginUseCase(
        api: LoginApi,
        userRepository: UserRepository
    ): LoginUseCase = LoginUseCaseImpl(api, userRepository)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserAccountUseCase {
        return GetUserAccountUseCaseImpl(userRepository)
    }
}