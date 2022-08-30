package com.example.salesmanproblemproject

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.salesmanproblemproject.database.GradDB
import com.example.salesmanproblemproject.database.GradDBViewModel
import com.example.salesmanproblemproject.databinding.FragmentDodajGradBinding
import timber.log.Timber

class DodajGradFragment : Fragment() {

    private lateinit var gradViewModel: GradDBViewModel
    private lateinit var binding: FragmentDodajGradBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("Usao u dodaj grad")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dodaj_grad, container, false)

        gradViewModel = ViewModelProvider(this).get(GradDBViewModel::class.java)
        binding.dodajGrad.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val grad = binding.grad.text.toString()
        val drzava = binding.drzava.text.toString()
        val opis = binding.opis.text.toString()
        val latituda = binding.lat.text.toString()
        val longituda = binding.longi.text.toString()
        if(checkInput(grad,drzava,opis,latituda,longituda)) {
            val grad = GradDB(0,grad,drzava,opis,latituda.toDouble(), longituda.toDouble())
            gradViewModel.addGrad(grad)
            Toast.makeText(requireContext(),"Grad je uspješno dodan!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_dodajGradFragment_to_listaGradovaFragment)
        }
        else {
            Toast.makeText(requireContext(),"Sva polja su obavezna za unos!", Toast.LENGTH_LONG).show()

        }

    }

    private fun checkInput(grad:String, drzava: String, opis:String, latituda:String, longituda:String) :Boolean {
        if(TextUtils.isEmpty(grad) || TextUtils.isEmpty(drzava) || TextUtils.isEmpty(opis) || TextUtils.isEmpty(latituda)
            || TextUtils.isEmpty(longituda))
                return false
        return true
    }
}