package com.example.bin_check.common

import android.net.Uri

object Utils {
    fun getFormattedLink(link: String): String{
        if (!link.startsWith("http://") && !link.startsWith("https://")) {
            return "https://" + link
        }
        return link
    }

    fun getLinkUri(link: String): Uri {
        val formattedLink = getFormattedLink(link)
        return Uri.parse(formattedLink)
    }

    fun getTelephoneUriUri(phoneNumber: String): Uri = Uri.parse("tel:$phoneNumber")

    fun getGeoUri(latitude: Long, longitude: Long): Uri = Uri.parse("geo:${latitude},${longitude}")

    fun getFormattedString(string: String?): String? {
        if (string == null) return null
        val stringBuilder = StringBuilder(string)

        stringBuilder[0] = stringBuilder[0].uppercaseChar()


        return stringBuilder.toString()
    }
}