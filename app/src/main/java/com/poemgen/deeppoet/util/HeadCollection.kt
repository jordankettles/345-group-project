package com.poemgen.deeppoet.util

import com.poemgen.deeppoet.R

object HeadCollection {
    var heads: MutableList<Head> = mutableListOf()
    var currentHeadIndex = 0


    fun initializeHeads() {
        heads.add(Head("Owl", mutableListOf(R.drawable.anim_owl_idle),
                        mutableListOf(R.drawable.anim_owl_talk)))
        heads.add(Head("Dino", mutableListOf(R.drawable.anim_placeholder_dino_01),
                        mutableListOf(R.drawable.anim_placeholder_dino_02)))

        setSelectedHead(0)
    }

    fun setSelectedHead(index: Int) {
        if(index < heads.size) {
            currentHeadIndex = index
        } else {
            currentHeadIndex = heads.size - 1
        }
    }

    fun getSelectedHead(): Head {
        return heads[currentHeadIndex]
    }
}