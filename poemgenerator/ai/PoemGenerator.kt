package poemgenerator.ai

interface PoemGenerator {
    fun submitPrompt(prompt: String)
    fun getPoem(): String
}