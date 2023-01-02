package com.example.binlist_check.presentation.main_screen

import com.example.binlist_check.data.entity.CardData

data class MainScreenState(
    val cardData: CardData? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val showCardData: Boolean = false
)