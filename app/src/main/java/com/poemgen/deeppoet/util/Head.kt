package com.poemgen.deeppoet.util

/**
 * Stores data regarding head animation IDs.
 * IDs split into two lists for idle and talking animations.
 * @property name The name given to the string for display.
 * @property idleAnims Mutable list of AnimatedDrawable IDs
 * @property talkingAnims Similarly, list of animations for talking heads.
 */
class Head(private val name: String, private val idleAnims: MutableList<Int>, private val talkingAnims: MutableList<Int>) {
    /**
     * Gets a random drawable ID of an idle animation.
     * @return ID of idle animation drawable.
     */
    fun getRandomIdle(): Int {
        return idleAnims.random()
    }

    /**
     * Gets random drawable ID of talking animation.
     * @return ID of talking drawable.
     */
    fun getRandomTalking(): Int {
        return talkingAnims.random()
    }

    /**
     * Returns the name of the head.
     */
    fun getName(): String {
        return name
    }
}