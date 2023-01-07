package com.example.bin_check.presentation.history_screen

import com.example.bin_check.common.error_type.ErrorType
import com.example.bin_check.data.entity.CardData

data class HistoryScreenState (
    val queriesList: List<CardData> = emptyList(),
    val isLoading: Boolean = false,
    val errorType: ErrorType? = null
)