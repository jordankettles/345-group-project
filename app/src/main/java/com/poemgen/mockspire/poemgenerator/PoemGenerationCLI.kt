package com.poemgen.mockspire.poemgenerator

import com.poemgen.mockspire.poemgenerator.ai.MockGenerator

fun main() {
    println("WHOOOOO")

    var generator = MockGenerator()

    generator.submitPrompt("a prompt")
    println(generator.getPoem())

//    var list = listOf("lorem", "ipsum", "dolores")
//
//    File("stuff.txt").delete()
//    for (i in list) {
//        File("stuff.txt").appendText(i + "\n")
//    }
//
//    var listSecond: MutableList<String> = mutableListOf()
//    var file = File("stuff.txt")
//    file.forEachLine { listSecond.add(it)}
////    File("stuff.txt").forEachLine { listSecond.add(it)}
//
//    println(listSecond.toString())




}