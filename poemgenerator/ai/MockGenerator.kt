package poemgenerator.ai

import java.io.File

class MockGenerator : PoemGenerator {
//    (private val mockPoemList: MutableList<String>)

    private var mockPoemList: MutableList<String> = mutableListOf()
    private var prompt: String? = null

    init {
        File("poemgenerator/ai/mockpoems.txt").forEachLine { this.mockPoemList.add(it)}
    }

    override fun submitPrompt(prompt: String) {
        this.prompt = prompt

        Thread.sleep(5_000)
    }

    override fun getPoem(): String {
        return this.mockPoemList.random()
    }

}