package com.example.moviestream_ui_practice.utils

object Constant {
    const val FOUR = 4
    const val NINE = 9
    const val TWO = 2
    const val ONE = 1
    const val THIRTYTWO = 32
    const val THIRTY = 30
    const val FIFTYFOUR = 54
    const val TWENTYFOUR = 24
    const val ZERO = 0
    const val EIGHT = 8
    const val FORTYTHREE = 43
    const val FORTYONE = 41
    val EMAILPATTERN : Regex = Regex("String emailPattern = \"[a-zA-Z0-9._-]+@[a-z]+\\\\.com+[a-z]+\"")
    val PASSWORDPATTERN : Regex = Regex("String PasswordPatten = ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$")
}