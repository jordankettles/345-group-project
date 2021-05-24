package com.poemgen.mockspire.poemgenerator.record

class Poem (private val title: String, private val content: String) {

    fun getTitle(): String {
        return this.title
    }

    fun getText(): String {
        return this.content
    }

}