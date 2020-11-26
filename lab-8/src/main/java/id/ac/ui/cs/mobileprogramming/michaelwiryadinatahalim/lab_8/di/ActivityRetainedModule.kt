package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.service.PipeDreamService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class ActivityRetainedModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://e8dacc26f5b6d63579984932f78a6d20.m.pipedream.net")
            .addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providePipeDreamService(retrofit: Retrofit): PipeDreamService {
        return retrofit.create(PipeDreamService::class.java)
    }
}
