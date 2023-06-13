package com.example.api_mvvm.ui.theme.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_mvvm.data.Card
import com.example.api_mvvm.network.YugiOhApi
import com.example.api_mvvm.network.YugiOhApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CardsUiState{
    object Loading: CardsUiState

    data class Success (val cards : List<Card>) : CardsUiState

    object Error: CardsUiState

}

class CardsViewModel: ViewModel() {

    private var _uiState : MutableStateFlow<CardsUiState> = MutableStateFlow(CardsUiState.Loading)
    val uiState: StateFlow<CardsUiState> = _uiState.asStateFlow()

    init {
        getCards()
    }

    // Gera a lista de cartas e atualiza o ui state de sucesso
    private fun getCards(){
        viewModelScope.launch {
            try {
                _uiState.value = CardsUiState.Success(
                    YugiOhApi.retrofitService.getCards()
                )
            } catch (e: IOException){
                _uiState.value = CardsUiState.Error
            } catch (e: HttpException){
                _uiState.value = CardsUiState.Error
            }

        }
    }
}