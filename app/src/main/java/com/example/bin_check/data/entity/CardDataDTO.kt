package com.example.bin_check.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bin_check.common.Constants.TABLE_NAME
import com.example.bin_check.data.remote.response.Number
import com.example.bin_check.domain.entity.CardData


@Entity(tableName = TABLE_NAME)
data class CardDataDTO(
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


fun CardDataDTO.toCardData(): CardData {
    return CardData(
        id = id,
        bin = bin,
        number = number,
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country,
        bank = bank
    )
}
