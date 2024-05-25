package com.example.firstaiapp.di

import com.example.firstaiapp.BuildConfig
import com.example.firstaiapp.data.repository.RepositoryImpl
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiKey(): String {
        return BuildConfig.gemini_api_key
    }

    @Provides
    @Singleton
    fun provideGenerativeModel(apiKey: String): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = apiKey,
            safetySettings = listOf(
                SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH)
            )
        )
    }

    @Provides
    @Singleton
    fun provideContentRepository(generativeModel: GenerativeModel): RepositoryImpl {
        return RepositoryImpl(generativeModel)
    }
}
