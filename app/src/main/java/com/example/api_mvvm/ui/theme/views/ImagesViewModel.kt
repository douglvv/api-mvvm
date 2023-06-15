package com.example.api_mvvm.ui.theme.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_mvvm.data.LoremPicsum
import com.example.api_mvvm.network.LoremPicsumApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ImagesUiState{
    object Loading: ImagesUiState

    data class Success (val images : List<LoremPicsum>) : ImagesUiState

    object Error: ImagesUiState

}

class ImagesViewModel: ViewModel() {

    private var _uiState : MutableStateFlow<ImagesUiState> = MutableStateFlow(ImagesUiState.Loading)
    val uiState: StateFlow<ImagesUiState> = _uiState.asStateFlow()

    init {
        getLoremPicsumImages()
    }

    // Gera a lista de cartas e atualiza o ui state de sucesso
    private fun getLoremPicsumImages(){
        viewModelScope.launch {
            try {
                _uiState.value = ImagesUiState.Success( // Cria uma instancia da classe Success
                    LoremPicsumApi.retrofitService.getImages()
                )
            } catch (e: IOException){
                _uiState.value = ImagesUiState.Error
            } catch (e: HttpException){
                _uiState.value = ImagesUiState.Error
            }

        }
    }
}