package com.poemgen.deeppoet.util

import com.poemgen.deeppoet.R

/**
 * Static class of list of heads. Must be initialized before use.
 */
object HeadCollection {
    var heads: MutableList<Head> = mutableListOf()
    var currentHeadIndex = 0

    /**
     * Populates list with head drawable resource.
     */
    fun initializeHeads() {
        heads = mutableListOf()
        heads.add(Head("Owl", mutableListOf(R.drawable.anim_owl_idle),
                        mutableListOf(R.drawable.anim_owl_talk)))
        heads.add(Head("Dolphin", mutableListOf(R.drawable.anim_dolph_idle),
                        mutableListOf(R.drawable.anim_dolph_talk)))
        heads.add(Head("Octopus", mutableListOf(R.drawable.anim_octo_idle),
                        mutableListOf(R.drawable.anim_octo_talk)))
        heads.add(Head("Elephant", mutableListOf(R.drawable.anim_eleph_idle),
                        mutableListOf(R.drawable.anim_eleph_talk)))
        heads.add(Head("Dinosaur", mutableListOf(R.drawable.anim_dino_idle),
                        mutableListOf(R.drawable.anim_dino_talk)))
        heads.add(Head("Alien", mutableListOf(R.drawable.anim_alien_idle),
                        mutableListOf(R.drawable.anim_alien_talk)))

        setSelectedHead(0)
    }

    /**
     * Sets the selected head to be displayed.
     */
    fun setSelectedHead(index: Int) {
        if(index < heads.size) {
            currentHeadIndex = index
        } else {
            currentHeadIndex = heads.size - 1
        }
    }

    /**
     * Gets the current selected head object.
     */
    fun getSelectedHead(): Head {
        return heads[currentHeadIndex]
    }
}