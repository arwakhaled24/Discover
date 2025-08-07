package com.example.domain.usecases

import com.example.domain.repo.UserPreferencesRepository
import jakarta.inject.Inject

class GetLastSearchUseCase @Inject constructor(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(): String {
        return repository.getLastSearch()
    }
}