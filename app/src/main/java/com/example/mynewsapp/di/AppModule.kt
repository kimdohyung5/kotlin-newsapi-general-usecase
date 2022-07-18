package com.example.mynewsapp.di

import android.content.Context
import androidx.room.Room
import com.example.mynewsapp.data.local.NewsDatabase
import com.example.mynewsapp.data.remote.NewsAPI
import com.example.mynewsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase( @ApplicationContext context: Context) : NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, "aa.db").build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPI( retrofit: Retrofit) : NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }
}