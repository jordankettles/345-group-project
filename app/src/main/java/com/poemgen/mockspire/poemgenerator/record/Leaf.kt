package com.poemgen.mockspire.poemgenerator.record

class Leaf (private var parent: Leaf?, private val title: String, private val content: String) {
    //Records any children, parent, and the content.

    private var childList: MutableList<Leaf> = mutableListOf()

    init {
        this.parent?.addChild(this)
    }

    // We might not need this.
    fun addChild(child: Leaf) {
        child.setParent(this)
        this.childList.add(child)
    }

    // This might be even more necessary
    fun setParent(parent: Leaf) {
        this.parent = parent
    }

    // This is necessary for users pruning their trees
    fun removeChild(child: Leaf) {
        this.childList.remove(child)
    }

    fun getTitle(): String {
        return this.title
    }

    fun getText(): String {
        return this.content
    }

}