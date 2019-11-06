package com.stechlabs.Views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stechlabs.R
import com.stechlabs.models.NotesResponse
import kotlinx.android.synthetic.main.item.view.*

class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var clickListener:(NotesResponse)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflator=LayoutInflater.from(parent.context)
        val view=inflator.inflate(R.layout.item,parent,false)
        return MyViewHolder(view)

    }

    var notes= listOf<NotesResponse>()
    fun setList(notes:List<NotesResponse>){
        this.notes=notes
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun getNote(pos:Int):NotesResponse{
        return notes[pos]
    }
    fun clickEvent(listener:(NotesResponse)->Unit){
        clickListener=listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val note:NotesResponse=notes.get(position)
        holder.bind(note,clickListener)
    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var title:TextView=itemView.title
        var description:TextView=itemView.description
        var id:TextView=itemView.note_id

        fun bind(note:NotesResponse,listener:(NotesResponse)->Unit){
            this.apply {
                title.text=note.title
                description.text=note.description
                id.text=note.id.toString()
            }
            itemView.setOnClickListener{
                listener(note)
            }


        }
    }
}