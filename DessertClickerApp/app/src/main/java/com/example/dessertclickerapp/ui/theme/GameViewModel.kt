package com.example.dessertclickerapp.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked(dessertPrice: Int) {
        _uiState.update { currentState ->
            val newRevenue = currentState.revenue + dessertPrice
            val newDessertsSold = currentState.dessertsSold.inc()
            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold
            )
        }
    }
}