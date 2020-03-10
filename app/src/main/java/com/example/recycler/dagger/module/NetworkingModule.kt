package com.example.recycler.dagger.module

import com.example.recycler.BuildConfig
import com.example.recycler.api.ApiRepository
import com.example.recycler.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    companion object{
        private const val NYT_BASE_URL = "https://api.nytimes.com/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val url : HttpUrl = original.url().newBuilder()
                .addQueryParameter("api-key", BuildConfig.NYT_API_KEY)
                .build()

            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }
        val newBuilder = httpBuilder.build().newBuilder().addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)

        return newBuilder.build()
    }

    @Singleton
    @Provides
    fun providesRetrofitService(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NYT_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    @Provides @Singleton
    fun provideRepositoryAPI(apiService : ApiService): ApiRepository {
        return ApiRepository(apiService)
    }
}