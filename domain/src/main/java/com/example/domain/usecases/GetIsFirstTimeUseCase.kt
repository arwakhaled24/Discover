package com.example.domain.usecases

import com.example.domain.repo.UserPreferencesRepository
import jakarta.inject.Inject

class GetIsFirstTimeUseCase @Inject constructor(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.isFirstTime()
    }
}