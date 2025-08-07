package com.example.domain.usecases

import com.example.domain.repo.UserPreferencesRepository
import jakarta.inject.Inject

class SetIsFirstTimeUseCase @Inject constructor(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(isFirstTime: Boolean) {
        repository.setFirstTime(isFirstTime)
    }
}