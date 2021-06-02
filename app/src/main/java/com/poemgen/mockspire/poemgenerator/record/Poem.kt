package com.poemgen.mockspire.poemgenerator.record

// Basic poem class. Holds a title and a content text.
/**
 * The poem class stores a poem.
 * @property title The title of the poem.
 * @property content The content of the poem.
 */
class Poem (private val title: String, private val content: String) {

    /**
     * Get Title.
     * @return Title of the poem.
     */
    fun getTitle(): String {
        return this.title
    }

    /**
     * Get Text.
     * @return Content of the poem.
     */
    fun getText(): String {
        return this.content
    }

}