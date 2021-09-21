package com.poemgen.deeppoet.poemgenerator.record

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
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
        var jsonModel: Array<Poem> = emptyArray()
        try {
            jsonModel = gson.fromJson(jsonInput, Array<Poem>::class.java)

            Log.d("filein", jsonInput)
        } catch (e: IllegalStateException) {
            Log.d("filein", "Deleting file")
            context.deleteFile("gardenstore")
        }

        seeds = jsonModel.toMutableList()
    }

    fun saveGarden(context: Context) {
        var gson = Gson()
        var array: Array<Poem> = seeds.toTypedArray()

        var jsonOut = gson.toJson(array)
        Log.d("filein", jsonOut)

        context.openFileOutput("gardenstore", Context.MODE_PRIVATE).use {
            it.write(jsonOut.toByteArray())
        }

    }
}