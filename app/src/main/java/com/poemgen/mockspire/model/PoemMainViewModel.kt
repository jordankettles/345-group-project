package com.poemgen.mockspire.model

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PoemMainViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _poemText = MutableLiveData<SpannableString>()
    val poemText: LiveData<SpannableString> = _poemText

    private val _readyForPrompt = MutableLiveData<Boolean>()
    val readyForPrompt: LiveData<Boolean> = _readyForPrompt



}