package com.example.cryptoapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.model.repository.CoinsListEvent
import com.example.cryptoapp.model.repository.UiEvent
import com.example.cryptoapp.view.ui.theme.MyBrown
import com.example.cryptoapp.view.ui.theme.MyCreamy
import com.example.cryptoapp.view.ui.theme.MyGreen
import com.example.cryptoapp.viewModel.CoinsListViewModel

@Composable
fun CoinsListScreen(
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: CoinsListViewModel = hiltViewModel()
){

    viewModel.getAllCoins()
    val allCoinsJson = viewModel.coins.collectAsState()

    LaunchedEffect(true){
        viewModel.uiEvent.collect{uiEvent ->
            when(uiEvent){
                is UiEvent.OnNavigate -> {
                    onNavigate(uiEvent)
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.getAllCoins()
                },
                containerColor = MyBrown,
                contentColor = MyCreamy
                ) {
                Icon(Icons.Filled.Refresh, contentDescription = "")
            }
        }
    ) { paddingValues ->
        allCoinsJson.value?.let {allCoinsJson ->
            LazyColumn(
                modifier = Modifier
                    .background(MyGreen)
                    .padding(paddingValues)
                    .padding(bottom = 40.dp)
            ){
                itemsIndexed(allCoinsJson.Data){index, coinData ->
                    val coinInfo = coinData.CoinInfo
                    val coinDisplay = coinData.DISPLAY
                    val coinPrice = coinDisplay?.USD
                    CoinItemInListScreen(
                        coinIndex = index,
                        coin = coinInfo,
                        price = coinPrice?.PRICE ?: "err",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(CoinsListEvent.OnCoinClick(coinInfo))
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }



}