package com.example.binlist_check.data.remote.response

import com.example.binlist_check.data.entity.Bank
import com.example.binlist_check.data.entity.CardData
import com.example.binlist_check.data.entity.Country


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
        scheme = scheme,
        type = type,
        brand = brand,
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

