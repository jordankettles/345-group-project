package com.poemgen.deeppoet

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.poemgen.deeppoet.poemgenerator.record.Garden
import com.poemgen.deeppoet.poemgenerator.record.Poem

/**
 *  Adapter for each item in PoemLog fragment.
 *  @property mPoems List of Poem objects to be displaying by recycler view.
 */
class PoemLogAdapter(private val mPoems: MutableList<Poem>) : RecyclerView.Adapter<PoemLogAdapter.ViewHolder>() {

    /**
     * Contains information pertaining to each poem that is displayed.
     * @param listItemView The view that the items will be inserted in.
     * @property titleTextView The TextView displaying the title of the poem.
     * @property contentTextView The TextView displaying the content of the poem.
     */
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.poem_title)
        val contentTextView = itemView.findViewById<TextView>(R.id.poem_text)
        val poemDeleteButton = itemView.findViewById<AppCompatImageButton>(R.id.poem_delete_button)
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

        val buttonViewDelete = holder.poemDeleteButton
        buttonViewDelete.setOnClickListener{
            Log.d("UI", "Delete Clicked")
            Log.d("UI", Garden.seeds.size.toString())
            Garden.seeds.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, Garden.seeds.size)
            Log.d("UI", Garden.seeds.size.toString())
        }
    }

    override fun getItemCount(): Int {
        return mPoems.size
    }
}