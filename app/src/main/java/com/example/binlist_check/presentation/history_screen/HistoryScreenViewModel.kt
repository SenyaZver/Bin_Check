package com.example.binlist_check.presentation.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist_check.common.Status
import com.example.binlist_check.domain.usecase.ClearPastQueriesUseCase
import com.example.binlist_check.domain.usecase.GetPastQueriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val getPastQueriesUseCase: GetPastQueriesUseCase,
    private val clearPastQueriesUseCase: ClearPastQueriesUseCase

): ViewModel() {
    private val _state = MutableStateFlow(HistoryScreenState())
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO) {
            getPastQueriesUseCase.execute().collect { status ->
                when (status) {
                    is Status.Loading -> {
                        _state.update {
                            HistoryScreenState(isLoading = true)
                        }
                    }
                    is Status.Success -> {
                        _state.update {
                            HistoryScreenState(queriesList = status.data ?: emptyList())
                        }
                    }
                    is Status.Error -> {
                        _state.update {
                            HistoryScreenState(errorMessage = status.message)
                        }
                    }
                }
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            clearPastQueriesUseCase.execute().collect { status ->
                when (status) {
                    is Status.Loading -> {}
                    is Status.Success -> {
                        _state.update {
                            HistoryScreenState(
                                queriesList = emptyList()
                            )
                        }
                    }
                    is Status.Error -> {
                        _state.update {
                            HistoryScreenState(
                                errorMessage = status.message
                            )
                        }
                    }
                }
            }
        }
    }


}