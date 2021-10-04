package com.poemgen.deeppoet.poemgenerator.record

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import java.io.FileNotFoundException
import java.util.stream.Collectors

private const val FILE_NAME = "gardenstore"

/**
 * the Garden object stores the poems for the current session in a list.
 */
object Garden {

    var seeds: MutableList<Poem> = mutableListOf()

    /**
     * Loads the Poems stored locally on the device, if they exist.
     * @param context The current context of the Application.
     */
    fun loadGarden(context: Context) {
        var jsonInput = ""
        try {
            jsonInput = context.openFileInput(FILE_NAME).bufferedReader().lines().collect(Collectors.joining())
        } catch (e: FileNotFoundException) {
            Log.d("filein", "No file")
        }

        val gson = Gson()
        var jsonModel: Array<Poem> = emptyArray()
        try {
            jsonModel = gson.fromJson(jsonInput, Array<Poem>::class.java)

            Log.d("filein", jsonInput)
        } catch (e: IllegalStateException) {
            Log.d("filein", "Deleting file")
            context.deleteFile(FILE_NAME)
        }

        seeds = jsonModel.toMutableList()
    }

    /**
     * Saves generated poems to the device.
     * @param context The current context of the Application.
     */
    fun saveGarden(context: Context) {
        val gson = Gson()
        val array: Array<Poem> = seeds.toTypedArray()

        val jsonOut = gson.toJson(array)
        Log.d("filein", jsonOut)

        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(jsonOut.toByteArray())
        }

    }

    /**
     * Deletes all locally stored poems.
     * Should only be used in test functions to ensure reproducible tests.
     * @param context The current context of the Application.
     */
    fun deleteGarden(context: Context) {
        context.deleteFile(FILE_NAME)
    }
}