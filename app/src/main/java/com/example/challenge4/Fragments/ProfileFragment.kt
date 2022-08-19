package com.example.challenge4.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challenge4.R
import com.example.challenge4.databinding.FragmentProfileBinding
import com.example.challenge4.datalocal.Data


class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)

        checkLogin()

        binding!!.btnLogOut.setOnClickListener {
            logout()
        }
    }

    private fun isLogin(): Boolean? {
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)

        return pref?.getBoolean(Data.Preferences.IS_LOGIN, false)
    }

    private fun checkLogin(){
        if (isLogin() == false){
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }
    }

    private fun logout(){
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor?.remove(Data.Preferences.IS_LOGIN)
        editor?.apply()
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
    }
}