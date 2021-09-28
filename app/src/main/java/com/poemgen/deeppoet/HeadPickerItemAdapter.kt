package com.poemgen.deeppoet

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import com.poemgen.deeppoet.placeholder.PlaceholderContent.PlaceholderItem
import com.poemgen.deeppoet.util.Head
import com.poemgen.deeppoet.util.HeadCollection

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 */
class HeadPickerItemAdapter(
    private val values: MutableList<Head>
) : RecyclerView.Adapter<HeadPickerItemAdapter.ViewHolder>() {

    /**
     * Template for all the views inside each head entry.
     */
    inner class ViewHolder(listItemView: View) :
        RecyclerView.ViewHolder(listItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.head_picker_item_imageview)
        val contentView: TextView = itemView.findViewById(R.id.head_name_textview)
        val itemContainerView: ConstraintLayout = itemView.findViewById(R.id.head_picker_item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val headPickerView = inflater.inflate(R.layout.fragment_head_picker_item, parent, false)
        return ViewHolder(headPickerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.imageView.setImageResource(HeadCollection.heads[position].getRandomIdle())
        (holder.imageView.drawable as AnimationDrawable).start()
        holder.contentView.text = item.getName()
        holder.itemContainerView.setBackgroundColor(Color.TRANSPARENT)

        if(position == HeadCollection.currentHeadIndex) {
            holder.itemContainerView.setBackgroundColor(Color.LTGRAY)
        }

        holder.itemContainerView.setOnClickListener{
            Log.d("UI", position.toString())
            HeadCollection.setSelectedHead(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = values.size

}