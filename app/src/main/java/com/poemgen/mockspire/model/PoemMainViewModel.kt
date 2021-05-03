package com.poemgen.mockspire.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poemgen.mockspire.poemgenerator.ai.MockGenerator

class PoemMainViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> =_title

    private val _poemText = MutableLiveData<String>()
    val poemText: LiveData<String> = _poemText

    private val poemGenerator = MockGenerator()


    fun submitPrompt(prompt: String) {
        poemGenerator.submitPrompt(prompt)
        this._poemText.value = poemGenerator.getPoem()
    }

}