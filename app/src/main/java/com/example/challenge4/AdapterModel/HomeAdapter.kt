package com.example.challenge4.AdapterModel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge4.Fragments.HomeFragmentDirections
import com.example.challenge4.datalocal.NotesEntity
import com.example.challenge4.databinding.ListNotesBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var list = emptyList<NotesEntity>()
    class ViewHolder(val binding: ListNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = list[position]
        holder.binding.tvTitle.text = type.title
        holder.binding.tvIsiNotes.text = type.content
        holder.binding.rowLayout.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(type)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(notes: List<NotesEntity>){
        this.list = notes
        notifyDataSetChanged()
    }
}