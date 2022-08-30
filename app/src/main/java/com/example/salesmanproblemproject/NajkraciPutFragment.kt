package com.example.salesmanproblemproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.example.salesmanproblemproject.algoritam.Algoritam
import com.example.salesmanproblemproject.database.GradDB
import com.example.salesmanproblemproject.database.GradDBViewModel
import com.example.salesmanproblemproject.databinding.FragmentNajkraciPutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


class NajkraciPutFragment : Fragment() {

    lateinit var gradViewModel: GradDBViewModel
    lateinit var l: List<GradDB>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNajkraciPutBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_najkraci_put, container,false)

        var args = NajkraciPutFragmentArgs.fromBundle(requireArguments())
        var algoritam = Algoritam(args.niz.lista)


            algoritam.najkraciPut.observe(viewLifecycleOwner, Observer { put ->
            var putString = ""
            for(i in 0..put.size-1) {
                if (i != put.size-1)
                    putString += put[i].grad + " - "
                else putString += put[i].grad
            }
            binding.put.text = putString
        })

        algoritam.algoritamZavrsen.observe(viewLifecycleOwner, Observer { nova ->
            binding.prikaziPutNaKarti.isClickable = true
        })

        binding.prikaziPutNaKarti.setOnClickListener {
            if (!algoritam.algoritamZavrsen.value!!) {
                Toast.makeText(
                    requireContext(),
                    "Algoritam prvo mora pronaći put!",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                var l = ListaGradova(algoritam.najkraciPut.value!!)
                it.findNavController()
                    .navigate(NajkraciPutFragmentDirections.actionNajkraciPutFragmentToPrikaziPut(l))

            }
        }
        binding.racunaj.setOnClickListener {
            algoritam.najkraciPutFunkcija()

        }

        return binding.root

    }

}