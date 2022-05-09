package com.example.moviestream_ui_practice.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View

fun colorMyText(inputText: String, startIndex: Int, endIndex: Int, textColor: Int, handler: () -> Unit): Spannable {
    val outPutColoredText: Spannable = SpannableString(inputText)
    outPutColoredText.setSpan(ClickHandler(handler), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    outPutColoredText.setSpan(
        ForegroundColorSpan(textColor), startIndex, endIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return outPutColoredText
}

class ClickHandler(

    private val handler: () -> Unit) : ClickableSpan() {
    override fun onClick(view: View) {
        handler()
    }

    override fun updateDrawState(drawState: TextPaint) {
        super.updateDrawState(drawState)
        drawState.isUnderlineText = false
    }
}