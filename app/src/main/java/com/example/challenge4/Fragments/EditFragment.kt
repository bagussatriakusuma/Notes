package com.example.challenge4.Fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challenge4.R
import com.example.challenge4.databinding.FragmentEditBinding
import com.example.challenge4.datalocal.NotesEntity

class EditFragment : DialogFragment() {
    lateinit var binding: FragmentEditBinding
    private val args by navArgs<EditFragmentArgs>()
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        binding.etTitleEdit.setText(args.currentNotes.title)
        binding.etContentEdit.setText(args.currentNotes.content)

        binding.btnCustomedit.setOnClickListener {
            editNotes()
        }

        binding.ivDelete.setOnClickListener {
           deleteNotes()
        }
    }

    private fun editNotes(){
        val title = binding.etTitleEdit.text.toString()
        val content = binding.etContentEdit.text.toString()

        if(inputCheck(title, content)){
            val updatedNotes = NotesEntity(args.currentNotes.id ,title, content)
            notesViewModel.updateNotes(updatedNotes)
            Toast.makeText(context, "Berhasil edit note", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }else{
            Toast.makeText(context,"Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, content: String): Boolean{
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(content))
    }

    private fun deleteNotes(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya"){ _, _ ->
            notesViewModel.deleteNotes(args.currentNotes)
            Toast.makeText(requireContext(), "Berhasil hapus note ${args.currentNotes.title}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }
        builder.setNegativeButton("Tidak"){ _, _ -> }
        builder.setTitle("Hapus ${args.currentNotes.title}?")
        builder.setMessage("Apakah kamu yakin untuk menghapus ${args.currentNotes.title}?")
        builder.create().show()
    }

}