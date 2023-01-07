package com.example.binlist_check.common.error_type

import androidx.annotation.StringRes
import com.example.binlist_check.R

enum class ErrorType(@StringRes val messageRes: Int) {
    SQLiteError(R.string.sql_lite_exception_message),
    LoadCardDataIoError(R.string.load_card_data_io_exception_message),
    LoadQueriesIoError(R.string.load_queries_io_exception_message),
    ActivityNotFoundError(R.string.activity_not_found_exception_message),
    HttpError(R.string.http_exception_message),
    WrongInput(R.string.wrong_input_message)
}
