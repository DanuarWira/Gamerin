package com.example.gamerin.di

import com.example.gamerin.data.GameRepository

object Injection {
    fun provideRepository(): GameRepository {
        return GameRepository.getInstance()
    }
}