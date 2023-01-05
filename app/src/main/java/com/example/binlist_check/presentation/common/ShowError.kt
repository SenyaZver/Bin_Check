package com.example.binlist_check.presentation.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.binlist_check.common.error_type.ErrorType

@Composable
fun ShowError(
    errorType: ErrorType?,
    toastLength: Int = Toast.LENGTH_SHORT,
    onErrorReceived: () -> Unit = {}
) {
    if (errorType!= null) {
        val context = LocalContext.current

        val message = errorType.messageRes
        Toast.makeText(context, message, toastLength).show()

        LaunchedEffect(errorType) {
            onErrorReceived()
        }
    }

}