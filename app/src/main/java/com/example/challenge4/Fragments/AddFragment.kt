package com.example.challenge4.Fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.datalocal.NotesEntity
import com.example.challenge4.databinding.FragmentAddBinding

class AddFragment : DialogFragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater,container,false)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnAddNotes.setOnClickListener {
            addDataToDatabase()
        }

        return binding.root
    }

    private fun addDataToDatabase(){
        val title = binding.tvTitleAdd.text.toString()
        val content = binding.tvContent.text.toString()

        if(inputCheck(title, content)){
            val notes = NotesEntity(0, title, content)
            notesViewModel.insertNotes(notes)
            Toast.makeText(context, "Berhasil menambahkan note", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }else{
            Toast.makeText(context, "Kolom masih kosong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, content: String): Boolean{
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(content))
    }


}