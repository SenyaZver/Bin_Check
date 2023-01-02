package com.example.binlist_check.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist_check.common.Constants.wrongInputMessage
import com.example.binlist_check.common.Status
import com.example.binlist_check.domain.usecase.GetCardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCardDataUseCase: GetCardDataUseCase
):ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    fun getCardData(binInput: String) {

        viewModelScope.launch {
            val bin = binInput.toLongOrNull()

            if (bin == null) {
                _state.update {
                    MainScreenState(
                        errorMessage = wrongInputMessage
                    )
                }
                return@launch
            }

            getCardDataUseCase.execute(bin).collect { status ->

                when (status) {
                    is Status.Loading -> {
                        _state.update {
                            MainScreenState(isLoading = true)
                        }
                    }
                    is Status.Success -> {
                        _state.update {

                            MainScreenState(
                                cardData = status.data,
                                showCardData = true
                            )
                        }
                    }
                    is Status.Error -> {
                        _state.update {
                            MainScreenState(
                                errorMessage = status.message
                            )
                        }
                    }
                }


            }
        }
    }
}