package com.poemgen.mockspire.poemgenerator.record

class Garden {

    private var seeds: MutableList<Leaf> = mutableListOf()

    companion object Trees {
        fun create(): Garden = Garden()
    }

}