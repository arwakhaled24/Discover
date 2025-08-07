package com.example.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.SetIsFirstTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val setFirstTimeUseCase: SetIsFirstTimeUseCase,
) : ViewModel() {
    fun markOnboardingDone() {
        viewModelScope.launch {
            setFirstTimeUseCase(false)
        }
    }
}
