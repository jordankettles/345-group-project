package com.poemgen.mockspire.poemgenerator.record

import android.util.Log

object Garden {

    public var seeds: MutableList<Poem> = mutableListOf()

    fun addDummyPoems() {
        var tempList = arrayListOf<Poem>()

        tempList.add(Poem("whoah", "Alas, poor Yorek, he died a camel."))
        tempList.add(Poem("Wew lad", "Stuff happened. Wew. "))
        tempList.add(Poem("Brigalow", "Bring me my coffee, said I. But he did not. What shame. He is now dead. "))
        this.seeds = tempList

        Log.d("DEBUG", "Doing")
        Log.d("DEBUG", Garden.seeds.toString())


    }

}