package com.example.bin_check.domain.entity

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.example.bin_check.data.entity.Bank
import com.example.bin_check.data.entity.Country
import com.example.bin_check.data.remote.response.Number

data class CardData (
    val id: Long = 0L,
    val bin: Long,
    val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)