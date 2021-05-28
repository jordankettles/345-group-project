package com.poemgen.mockspire.poemgenerator.record

import android.util.Log

// Shared static class across the whole app to store poems
// Recycler view does not work with ViewModel because design is squat.
object Garden {

    var seeds: MutableList<Poem> = mutableListOf()

    fun addDummyPoems() {
        seeds.add(Poem("whoah", "Alas, poor Yorek, he died a camel."))
        seeds.add(Poem("Wew lad", "Stuff happened. Wew. "))
        seeds.add(Poem("Brigalow", "Bring me my coffee, said I. But he did not. What shame. He is now dead. "))

        Log.d("DEBUG", "Doing")
        Log.d("DEBUG", seeds.toString())


    }

}