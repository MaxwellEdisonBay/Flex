package com.flexfitnesstestapp.utils

internal fun String.onlyLetters() = all { it.isLetter() }


internal fun String.validateNameField(allowBlank: Boolean = false) =
    !onlyLetters() || (if (allowBlank) false else isBlank())
