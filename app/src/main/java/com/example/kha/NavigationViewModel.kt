package com.example.kha

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetIsFirstTimeUseCase
import com.example.kha.navigations.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val isFirstTimeUseCase: GetIsFirstTimeUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        viewModelScope.launch {
            val isFirstTime = isFirstTimeUseCase()
            _startDestination.value = if (isFirstTime) {
                NavigationRoutes.Onboarding.route
            } else {
                NavigationRoutes.NewsList.route
            }
        }
    }
}
