package com.poemgen.deeppoet.util

class Head(private val idleAnims: MutableList<Int>, private val talkingAnims: MutableList<Int>) {
    fun getRandomIdle(): Int {
        return idleAnims.random()
    }

    fun getRandomTalking(): Int {
        return talkingAnims.random()
    }
}