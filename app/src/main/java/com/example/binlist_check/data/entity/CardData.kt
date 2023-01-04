package com.example.binlist_check.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.binlist_check.common.Constants.TABLE_NAME
import com.example.binlist_check.data.remote.response.Number


@Entity(tableName = TABLE_NAME)
data class CardData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val bin: Long,
    @Embedded(prefix = "number_") val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded(prefix = "country_") val country: Country?,
    @Embedded(prefix = "bank_") val bank: Bank?
)
