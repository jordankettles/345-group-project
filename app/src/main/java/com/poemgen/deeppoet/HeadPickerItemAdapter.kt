package com.poemgen.deeppoet

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.poemgen.deeppoet.placeholder.PlaceholderContent.PlaceholderItem
import com.poemgen.deeppoet.util.Head
import com.poemgen.deeppoet.util.HeadCollection

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class HeadPickerItemAdapter(
    private val values: MutableList<Head>
) : RecyclerView.Adapter<HeadPickerItemAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) :
        RecyclerView.ViewHolder(listItemView) {
        val idView: TextView = itemView.findViewById(R.id.item_number)
        val contentView: TextView = itemView.findViewById(R.id.head_name_textview)
        val itemContainerView: LinearLayout = itemView.findViewById(R.id.head_picker_item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val headPickerView = inflater.inflate(R.layout.fragment_head_picker_item, parent, false)
        return ViewHolder(headPickerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = position.toString()
        holder.contentView.text = item.getName()

        val itemContainer = holder.itemContainerView
        itemContainer.setOnClickListener{
            Log.d("UI", position.toString())
            HeadCollection.setSelectedHead(position)
        }
    }

    override fun getItemCount(): Int = values.size

}