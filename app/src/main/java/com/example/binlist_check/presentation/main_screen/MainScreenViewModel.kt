package com.example.binlist_check.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist_check.R
import com.example.binlist_check.common.Status
import com.example.binlist_check.common.StringProvider
import com.example.binlist_check.domain.usecase.GetCardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCardDataUseCase: GetCardDataUseCase,
    private val stringProvider: StringProvider
):ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    fun getCardData(binInput: String) {

        viewModelScope.launch {
            val bin = binInput.toLongOrNull()


            if (bin == null) {
                val message = stringProvider.provideString(R.string.wrong_input_message)

                _state.update {
                    MainScreenState(
                        errorMessage = message
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