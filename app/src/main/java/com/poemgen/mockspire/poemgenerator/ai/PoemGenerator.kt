package com.poemgen.mockspire.poemgenerator.ai

interface PoemGenerator {
    fun submitPrompt(prompt: String)
    fun getPoem(): String
}