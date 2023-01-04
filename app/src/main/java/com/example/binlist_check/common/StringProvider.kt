package com.example.binlist_check.common

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject


//TODO: a better solution would be to have this in a class app as a public class
class StringProvider @Inject constructor(
    private val context: Context
) {

    fun provideString(@StringRes res: Int): String {
        return context.getString(res)
    }
    fun provideStringWithArgument(@StringRes res: Int, arg: Int): String {
        return context.getString(res, arg)
    }
}