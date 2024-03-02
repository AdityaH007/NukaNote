package com.example.nukanote.com.example.nukanote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nukanote.com.example.nukanote.data.entity.Notes
import com.example.nukanote.databinding.ItemNotesBinding
import java.text.SimpleDateFormat

class NoteAdapter(private val mNotes:List<Notes>,private  val listener: OnNoteClickListener): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    interface OnNoteClickListener{
        fun onNoteClick(notes: Notes)
        fun onNoteLongClick(notes: Notes)
    }

    inner class ViewHolder(private val binding:ItemNotesBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.apply {
                root.setOnClickListener{
                    val position = adapterPosition
                    if(position!=RecyclerView.NO_POSITION){
                        val note = mNotes[position]
                        listener.onNoteClick(note)
                    }
                }

                root.setOnLongClickListener {
                    val position =adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val note=mNotes[position]
                        listener.onNoteLongClick(note)
                    }
                    true
                }
            }
        }

        fun bind(notes: Notes){
            binding.apply {
                notetitle.text=notes.title
                notecontent.text=notes.content
                val formatter=SimpleDateFormat("dd/mm/yyyy")
                notedate.text=formatter.format(notes.date)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val  binding=ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return  mNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(mNotes[position])
        {
            holder.bind(this)
        }
    }
}