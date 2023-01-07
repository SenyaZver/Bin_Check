package com.example.bin_check.data.remote.response

import com.example.bin_check.common.Utils.getFormattedString
import com.example.bin_check.data.entity.Bank
import com.example.bin_check.data.entity.CardData
import com.example.bin_check.data.entity.Country


data class BinResponse (
    val number: Number,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)

fun BinResponse.toCardData(bin: Long): CardData {
    return CardData(
        bin = bin,
        scheme = getFormattedString(scheme),
        type = getFormattedString(type),
        brand = getFormattedString(brand),
        prepaid = prepaid,
        bank = bank,
        country = country,
        number = number
    )
}


data class Number (
    val length: Long,
    val luhn: Boolean
)

