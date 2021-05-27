package com.poemgen.mockspire.poemgenerator.ai

class MockGenerator : PoemGenerator {
//    (private val mockPoemList: MutableList<String>)

    private var mockPoemList: MutableList<String> = mutableListOf()
    private var prompt: String? = null

    init {
        mockPoemList = mutableListOf(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas sed tempus urna et pharetra. Blandit volutpat maecenas volutpat blandit aliquam etiam. Adipiscing elit ut aliquam purus sit amet luctus venenatis lectus.",
            "Eget felis eget nunc lobortis mattis. Ornare aenean euismod elementum nisi. Ut ornare lectus sit amet est placerat in egestas erat. Consectetur adipiscing elit duis tristique sollicitudin nibh. Aliquam faucibus purus in massa tempor nec feugiat. Sed faucibus turpis in eu.",
            "Sit amet consectetur adipiscing elit duis tristique sollicitudin nibh.",
            "Vitae proin sagittis nisl rhoncus. Purus semper eget duis at tellus at urna. Velit aliquet sagittis id consectetur purus ut faucibus pulvinar. Turpis nunc eget lorem dolor. Nulla pellentesque dignissim enim sit amet. Varius quam quisque id diam vel quam elementum.",
            "Gravida cum sociis natoque penatibus et. Urna molestie at elementum eu facilisis sed.",
            "Bibendum est ultricies integer quis auctor elit sed vulputate. Donec adipiscing tristique risus nec feugiat in fermentum posuere urna. Egestas sed tempus urna et pharetra pharetra massa. Arcu non odio euismod lacinia at. Sagittis orci a scelerisque purus semper.",
            "Quam quisque id diam vel quam. Tristique senectus et netus et malesuada fames ac turpis. Quam nulla porttitor massa id neque aliquam vestibulum morbi blandit. Tempus iaculis urna id volutpat lacus. Lobortis feugiat vivamus at augue. Aliquam nulla facilisi cras fermentum."
        )
    }

    override fun submitPrompt(prompt: String) {
        this.prompt = prompt

        Thread.sleep(1_000)
    }

    override fun getPoem(): String {
        return this.mockPoemList.random()
    }

}