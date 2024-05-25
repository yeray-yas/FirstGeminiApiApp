package com.example.firstaiapp.data.repository

import com.google.ai.client.generativeai.GenerativeModel

class RepositoryImpl(private val generativeModel: GenerativeModel) : Repository {
    override suspend fun generateContent(prompt: String): String? {
        return generativeModel.generateContent(prompt).text
    }
}