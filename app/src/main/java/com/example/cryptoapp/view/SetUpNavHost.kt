package com.example.cryptoapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cryptoapp.utils.Constants
import com.example.cryptoapp.utils.Constants.COINS_LIST_SCREEN
import com.example.cryptoapp.utils.Constants.COIN_DETAILS_SCREEN
import com.example.cryptoapp.view.screens.CoinDetailScreen
import com.example.cryptoapp.view.screens.CoinsListScreen

@Composable
fun SetUpNavHost(
    navController: NavHostController
){

    NavHost(
        navController = navController,
        startDestination = COINS_LIST_SCREEN,
    ){
        composable(
            route = COINS_LIST_SCREEN
        ){
            CoinsListScreen(
                onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(
            route = "$COIN_DETAILS_SCREEN?symbol={symbol}",
            arguments = listOf(
                navArgument("symbol"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol").toString()
            CoinDetailScreen(symbol = symbol)
        }
    }


}