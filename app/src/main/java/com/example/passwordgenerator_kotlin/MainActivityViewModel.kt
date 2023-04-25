package com.example.passwordgenerator_kotlin

import androidx.lifecycle.ViewModel
import java.lang.StringBuilder

class MainActivityViewModel : ViewModel() {
    private val lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
    private val uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val digits = "0123456789"
    private val symbols = "!@#$%&*()_+-=[]|,./?><"

    fun generatePassword(
        length: Int,
        isNumberIncluded: Boolean,
        isUppercaseLetterIncluded: Boolean,
        isSymbolIncluded: Boolean
    ): String {
        val password = StringBuilder(length)
        repeat(3) { password.append(lowercaseLetters.random()) }
        if (isNumberIncluded) repeat(2) { password.append(digits.random()) }
        if (isUppercaseLetterIncluded) repeat(2) { password.append(uppercaseLetters.random()) }
        if (isSymbolIncluded) repeat(1) { password.append(symbols.random()) }
        val remainingChars = length - password.length
        val constants = getConstants(isNumberIncluded, isUppercaseLetterIncluded, isSymbolIncluded)
        repeat(remainingChars) {
            password.append(constants.random().random())
        }
        val shuffledPassword = password.toMutableList().run {
            shuffle()
            joinToString("","","")
        }
        return shuffledPassword
    }

    private fun getConstants(
        numberIncluded: Boolean,
        uppercaseLetterIncluded: Boolean,
        symbolIncluded: Boolean
    ): List<String> {
        val constants = mutableListOf(lowercaseLetters)
        when {
            numberIncluded -> constants.add(digits)
            uppercaseLetterIncluded -> constants.add(uppercaseLetters)
            symbolIncluded -> constants.add(symbols)
        }

        return constants
    }

}