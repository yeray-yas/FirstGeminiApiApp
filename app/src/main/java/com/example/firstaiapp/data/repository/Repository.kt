package com.example.firstaiapp.data.repository

interface Repository {
    suspend fun generateContent(prompt: String): String?
}