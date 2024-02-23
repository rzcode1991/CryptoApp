package com.example.cryptoapp.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.model.data.dto2.detail.CoinFullDetail
import com.example.cryptoapp.model.repository.CoinDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinDetailRepository
): ViewModel() {


    val coinFullDetail = MutableStateFlow<CoinFullDetail?>(null)

    fun getCoinDetail(symbol: String){
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("coroutine error", throwable.message.toString())
        }
        viewModelScope.launch(
            Dispatchers.IO + errorHandler
        ) {
            val response = repository.getCoinBySymbol(symbol)
            withContext(Dispatchers.Main){
                if (response.isSuccessful && response.body() != null){
                    response.body()?.let {
                        coinFullDetail.emit(it)
                    }
                }
            }
        }
    }


}