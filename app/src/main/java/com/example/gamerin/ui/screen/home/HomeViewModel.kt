package com.example.gamerin.ui.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerin.data.GameRepository
import com.example.gamerin.model.GameCart
import com.example.gamerin.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GameRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<GameCart>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GameCart>>>
        get() = _uiState

    fun getAllGames() {
        viewModelScope.launch {
            repository.getAllGames()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { gameCart ->
                    _uiState.value = UiState.Success(gameCart)
                }
        }
    }
}