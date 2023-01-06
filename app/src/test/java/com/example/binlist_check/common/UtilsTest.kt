package com.example.binlist_check.common

import org.junit.Assert.*

import org.junit.Test

class UtilsTest {

    @Test
    fun getFormattedLink() {
        val link = "www.google.com"
        val formattedLink = Utils.getFormattedLink(link)

        assertEquals(formattedLink, "https://www.google.com")
    }

    @Test
    fun getFormattedLinkFromCorrectLink() {
        val link = "https://www.google.com"
        val formattedLink = Utils.getFormattedLink(link)
        assertEquals(formattedLink, "https://www.google.com")
    }

    @Test
    fun getFormattedString() {
        val string = "string"
        val formattedString = Utils.getFormattedString(string)
        assertEquals(formattedString, "String")
    }
}