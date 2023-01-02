package com.example.binlist_check.presentation.main_screen

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.binlist_check.presentation.Routes.historyScreenRoute


@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()


    val currentBinInput = rememberSaveable{ mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                text = "BIN/INN Checker",
                fontSize = 30.sp
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Input your bin!",
            fontSize = 20.sp
        )

        Spacer(Modifier.height(20.dp))


        TextField(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(8.dp)),
            value = currentBinInput.value,
            onValueChange = {
                currentBinInput.value = it
            },
            label = {
                Text(text = "BIN/INN:")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    viewModel.getCardData(currentBinInput.value)
                }
            )
        )

        Spacer(Modifier.height(20.dp))

        if (state.value.isLoading) {
            Spacer(Modifier.weight(1f))
            CircularProgressIndicator()
            Spacer(Modifier.weight(1f))
        }

        if (state.value.cardData!=null) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    BankCard(
                        cardData = state.value.cardData!!
                    )
                }

            }

        }



        Button(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = {
                navController.navigate(historyScreenRoute)
            }
        ) {
            Text(
                text = "History of previous queries",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


    }

}