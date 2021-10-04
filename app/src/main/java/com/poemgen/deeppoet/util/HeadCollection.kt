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
        heads.add(Head("Dino", mutableListOf(R.drawable.anim_placeholder_dino_01),
                        mutableListOf(R.drawable.anim_placeholder_dino_02)))

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