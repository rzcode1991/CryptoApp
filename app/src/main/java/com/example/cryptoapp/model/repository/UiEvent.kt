package com.example.cryptoapp.model.repository

sealed class UiEvent {

    data class OnNavigate(val route: String): UiEvent()
    object OnBackStack: UiEvent()

}