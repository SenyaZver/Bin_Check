package com.example.binlist_check.presentation.history_screen

import com.example.binlist_check.data.entity.CardData

data class HistoryScreenState (
    val queriesList: List<CardData>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)