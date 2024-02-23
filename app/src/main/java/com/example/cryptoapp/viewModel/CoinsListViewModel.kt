package com.example.cryptoapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.model.data.dto2.list.AllCoinsJson
import com.example.cryptoapp.model.repository.CoinsListEvent
import com.example.cryptoapp.model.repository.CoinsListRepository
import com.example.cryptoapp.model.repository.UiEvent
import com.example.cryptoapp.utils.Constants.COIN_DETAILS_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val repository: CoinsListRepository
): ViewModel() {


    private val allCoinsJson = MutableStateFlow<AllCoinsJson?>(null)
    val coins = allCoinsJson.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun getAllCoins(){
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("coroutine error", throwable.message.toString())
        }
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            val response = repository.getAllCoins()
            withContext(Dispatchers.Main + errorHandler){
                if (response.isSuccessful && response.body() != null){
                    response.body()?.let { allCoins ->
                        allCoinsJson.emit(allCoins)
                    }
                }
            }
        }
    }

    // from UI to ViewModel
    fun onEvent(event: CoinsListEvent){
        when(event){
            is CoinsListEvent.OnCoinClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate("$COIN_DETAILS_SCREEN?symbol=${event.coinInfo.Name}"))
                }
            }
        }
    }


}