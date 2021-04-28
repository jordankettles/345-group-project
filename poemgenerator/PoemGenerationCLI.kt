package poemgenerator

import poemgenerator.ai.MockGenerator

fun main() {
    println("WHOOOOO")

    var generator = MockGenerator()

    generator.submitPrompt("a prompt")
    println(generator.getPoem())

//    var list = listOf("lorem", "ipsum", "dolores")

//    File("stuff.txt").delete()
//    for (i in list) {
//        File("stuff.txt").appendText(i + "\n")
//    }

//    var listSecond: MutableList<String> = mutableListOf()
//
//    File("stuff.txt").forEachLine { listSecond.add(it)}
//
//    println(listSecond.toString())




}