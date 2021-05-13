package com.poemgen.mockspire.poemgenerator.controller

class PoemManager {

    // Replace with actual poem generator later.
//    private var generator: PoemGenerator = MockGenerator()

    // poemOutput is null until MockGenerator returns poem.
    private var poemOutput: String? = null

    init {
        // TODO: Stuff

    }

    //submitPrompt
    fun submitPrompt(prompt: String) {
        // TODO: Stick this in a coroutine and wait for it to return a poem.
//        this.generator.submitPrompt(prompt)
    }

    //retrieve prompt
    fun getPoem(): String? {
        if(poemOutput != null) {
            return poemOutput as String
        }

        return null
    }

    //check if prompt ready
    fun isPoemReady(): Boolean {
        // TODO: Check if poem returned or not.

        return false
    }

    //some kind of text processing before prompt served to UI
}