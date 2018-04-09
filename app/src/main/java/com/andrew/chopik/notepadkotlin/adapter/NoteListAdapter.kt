package com.andrew.chopik.notepadkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.andrew.chopik.notepadkotlin.R
import com.andrew.chopik.notepadkotlin.model.Note

/**
 * Created by Andrew on 22.02.2018.
 */
class NoteListAdapter(private val itemClickListener: ItemClickListener,
                      private var notes: List<Note> = emptyList()
) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {

        private val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        private val itemText: TextView = itemView.findViewById(R.id.item_text)

        init {
            itemView.setOnClickListener { itemClickListener.onItemClicked(this) }
        }

        fun bind(note: Note) {
            itemTitle.text = note.title
            itemText.text = note.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(v, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    interface ItemClickListener {

        fun onItemClicked(viewHolder: ViewHolder)
    }
}
