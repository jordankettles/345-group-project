package com.poemgen.deeppoet.poemgenerator.record

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.FileNotFoundException
import java.util.stream.Collectors

/**
 * the Garden object stores the poems for the current session in a list.
 */
object Garden {

    var seeds: MutableList<Poem> = mutableListOf()

    fun loadGarden(context: Context) {
        var jsonInput = ""
        try {
            jsonInput = context.openFileInput("gardenstore").bufferedReader().lines().collect(Collectors.joining())
        } catch (e: FileNotFoundException) {
            Log.d("filein", "No file")
        }

        var gson = Gson()
        var jsonModel = gson.fromJson(jsonInput, Array<Poem>::class.java)
        seeds = jsonModel.toMutableList()

        Log.d("jsonin", seeds[0].getText())
        Log.d("filein", jsonInput)
    }

    fun saveGarden(context: Context) {
        var gson = Gson()
        var array: Array<Poem> = seeds.toTypedArray()

        var jsonOut = gson.toJson(array)
        Log.d("jsonout", jsonOut)

        context.openFileOutput("gardenstore", Context.MODE_PRIVATE).use {
            it.write(jsonOut.toByteArray())
        }

    }
}