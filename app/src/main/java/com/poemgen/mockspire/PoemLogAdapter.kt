package com.poemgen.mockspire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poemgen.mockspire.poemgenerator.record.Poem

/**
 *  Adapter for each item in PoemLog fragment.
 */
class PoemLogAdapter(private val mPoems: MutableList<Poem>) : RecyclerView.Adapter<PoemLogAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.poem_title)
        val contentTextView = itemView.findViewById<TextView>(R.id.poem_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemLogAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val poemLogView = inflater.inflate(R.layout.fragment_poem_log_item, parent, false)

        return ViewHolder(poemLogView)
    }

    override fun onBindViewHolder(holder: PoemLogAdapter.ViewHolder, position: Int) {
        val poem: Poem = mPoems.get(position)

        val textViewTitle = holder.titleTextView
        textViewTitle.setText(poem.getTitle())

        val textViewContent = holder.contentTextView
        textViewContent.setText(poem.getText())
    }

    override fun getItemCount(): Int {
        return mPoems.size
    }
}