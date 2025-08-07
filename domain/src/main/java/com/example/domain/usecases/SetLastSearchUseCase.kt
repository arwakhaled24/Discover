package com.example.domain.usecases

import com.example.domain.repo.UserPreferencesRepository
import jakarta.inject.Inject

class SetLastSearchUseCase @Inject constructor(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(keyword: String) {
        repository.setLastSearch(keyword)
    }
}
