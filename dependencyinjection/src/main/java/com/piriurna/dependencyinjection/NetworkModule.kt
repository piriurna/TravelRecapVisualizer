package com.piriurna.dependencyinjection

import com.piriurna.data.remote.GeocodingApi
import com.piriurna.data.remote.sources.GeocodingApiSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(@Named("api_key") apiKey: String) : OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor {
                var request = it.request()
                val url = request.url.newBuilder().addQueryParameter("api_key", apiKey).build()
                request = request.newBuilder().url(url).build()
                return@Interceptor it.proceed(request)
            })
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGeocodingApiSource(geocodingApi: GeocodingApi): GeocodingApiSource {
        return GeocodingApiSource(geocodingApi)
    }

    @Provides
    @Singleton
    fun provideGeocodingApi(okHttpClient: OkHttpClient): GeocodingApi {
        return Retrofit.Builder()
            .baseUrl("https://geocode.maps.co/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodingApi::class.java)
    }

}