package com.example.binlist_check.common

import android.net.Uri
import android.util.Log

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
}