package com.example.challenge4.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.challenge4.R
import com.example.challenge4.databinding.FragmentLoginBinding
import com.example.challenge4.datalocal.Data


class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLogin()

        binding!!.btnSignIn.setOnClickListener {
            getPrefData()
        }

        binding!!.tvRegister.setOnClickListener{
            navControllerRegister()
        }
    }

    private fun getPrefData(){
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)
        val prefName = pref.getString(Data.Preferences.PREF_NAME, "")
        val prefEmail = pref.getString(Data.Preferences.PREF_EMAIL, "")
        val prefPass = pref.getString(Data.Preferences.PREF_PASS, "")
        val email = binding!!.etEmailSignIn.text.toString()
        val pass = binding!!.etPassSignIn.text.toString()

        if (email.isEmpty() && pass.isEmpty()){
            Toast.makeText(context, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (email.isEmpty()) {
            Toast.makeText(context, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(email != prefEmail){
            Toast.makeText(context, "Email salah", Toast.LENGTH_SHORT).show()
        }else if (pass.isEmpty()) {
            Toast.makeText(context, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(pass != prefPass){
            Toast.makeText(context, "Password salah", Toast.LENGTH_SHORT).show()
        }else if(email == prefEmail && pass == prefPass){
            Toast.makeText(context, "Validasi berhasil. Selamat datang ${prefName}", Toast.LENGTH_SHORT).show()
            setLoggin(true)
            navControllerSignIn()
        }
    }

    private fun navControllerSignIn(){
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun navControllerRegister(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun setLoggin(isLogin: Boolean) {
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor?.putBoolean(Data.Preferences.IS_LOGIN, isLogin)
        editor?.apply()
    }

    private fun isLogin(): Boolean? {
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)

        return pref?.getBoolean(Data.Preferences.IS_LOGIN, false)
    }

    private fun checkLogin(){
        if (isLogin()!!){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}