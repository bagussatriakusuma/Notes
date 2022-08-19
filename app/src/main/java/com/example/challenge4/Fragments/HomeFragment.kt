package com.example.challenge4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.challenge4.AdapterModel.HomeAdapter
import com.example.challenge4.R
import com.example.challenge4.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter()
        binding!!.rvNotes.adapter = adapter

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        notesViewModel.readAllNotes.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setData(notes)
        })

        binding!!.fabInsert.setOnClickListener {
            val dialogFragment = AddFragment()
            dialogFragment.show(requireActivity().supportFragmentManager, null)
        }

        binding!!.rivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
            binding!!.rvNotes.layoutManager = this
        }

    }

}