package com.example.binlist_check

import com.example.binlist_check.common.Utils.getFormattedLink
import org.junit.Assert.assertEquals
import org.junit.Test


class FormattingLinkUnitTest {

    @Test
    fun linkFormattingIsCorrect() {
        val link = "www.google.com"
        val formattedLink = getFormattedLink(link)

        assertEquals(formattedLink, "https://www.google.com")
    }

    @Test
    fun correctLinkFormatting() {
        val link = "https://www.google.com"
        val formattedLink = getFormattedLink(link)
        assertEquals(formattedLink, "https://www.google.com")
    }
}