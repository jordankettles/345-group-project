package com.poemgen.mockspire.poemgenerator.record

// Basic poem class. Holds a title and a content text.
class Poem (private val title: String, private val content: String) {

    fun getTitle(): String {
        return this.title
    }

    fun getText(): String {
        return this.content
    }

}