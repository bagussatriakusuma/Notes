package com.example.challenge4.Fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.challenge4.R
import com.example.challenge4.databinding.FragmentRegisterBinding
import com.example.challenge4.datalocal.Data

class RegisterFragment : Fragment() {
    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.ivBack.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
        }

        binding!!.btnContinue.setOnClickListener {
            isDataExist()
        }
    }

    private fun putDataPref(){
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)

        val name = binding!!.etName.text.toString()
        val email = binding!!.etEmailSignUp.text.toString()
        val pass = binding!!.etPassSignUp.text.toString()
        val conf_pass = binding!!.etConfirmPassSignUp.text.toString()

        val editor = pref.edit()
        editor.putString(Data.Preferences.PREF_NAME, name)
        editor.putString(Data.Preferences.PREF_EMAIL, email)
        editor.putString(Data.Preferences.PREF_PASS, pass)
        editor.putString(Data.Preferences.PREF_CONFIRM_PASS, conf_pass)
        editor.apply()

        navControllerPutData()
    }

    private fun isDataExist(){
        val pref = requireActivity().getSharedPreferences(Data.Preferences.PREF_ID, Context.MODE_PRIVATE)

        val name = binding!!.etName.text.toString()
        val email = binding!!.etEmailSignUp.text.toString()
        val pass = binding!!.etPassSignUp.text.toString()
        val conf_pass = binding!!.etConfirmPassSignUp.text.toString()

        val prefName = pref.getString(Data.Preferences.PREF_NAME, "")
        val prefEmail = pref.getString(Data.Preferences.PREF_EMAIL, "")
        val prefPass = pref.getString(Data.Preferences.PREF_PASS, "")

        if (name.isEmpty() && email.isEmpty() && pass.isEmpty() && conf_pass.isEmpty()){
            Toast.makeText(context, "Kolom register tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(name.isEmpty()){
            Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(email.isEmpty()){
            Toast.makeText(context, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (email == prefEmail) {
            Toast.makeText(context, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(context, "Email tidak valid", Toast.LENGTH_SHORT).show()
        }else if(pass.isEmpty()){
            Toast.makeText(context, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (pass == prefPass) {
            Toast.makeText(context, "Password tidak boleh sama dengan yang sebelumnya", Toast.LENGTH_SHORT).show()
        }else if(pass.length < 8){
            Toast.makeText(context, "Password harus lebih dari 8 character", Toast.LENGTH_SHORT).show()
        }else if(!pass.matches(".*[A-Z].*".toRegex())){
            Toast.makeText(context, "Password harus mengandung 1 uppercase", Toast.LENGTH_SHORT).show()
        }else if(!pass.matches(".*[a-z].*".toRegex())) {
            Toast.makeText(context, "Password harus mengandung 1 lowercase", Toast.LENGTH_SHORT).show()
        }else if(conf_pass.isEmpty()){
            Toast.makeText(context, "Konfirmasi password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(conf_pass != pass){
            Toast.makeText(context, "Password tidak sama", Toast.LENGTH_SHORT).show()
        }else{
            val dialogCustom = Dialog(requireActivity())
            dialogCustom.setContentView(R.layout.custom_dialog_register)
            dialogCustom.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogCustom.show()

            dialogCustom.findViewById<Button>(R.id.btnYa).setOnClickListener{
                putDataPref()
                dialogCustom.dismiss()
            }
            dialogCustom.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                dialogCustom.dismiss()
            }
        }
    }

    private fun navControllerPutData(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}