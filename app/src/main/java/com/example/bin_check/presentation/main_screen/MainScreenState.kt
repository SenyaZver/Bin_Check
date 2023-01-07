package com.example.bin_check.presentation.main_screen

import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.domain.entity.CardData

data class MainScreenState(
    val cardData: CardData? = null,
    val errorType: ErrorType? = null,
    val isLoading: Boolean = false,
    val showCardData: Boolean = false
)
