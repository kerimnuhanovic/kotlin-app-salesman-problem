package com.example.salesmanproblemproject.algoritam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.salesmanproblemproject.R
import com.example.salesmanproblemproject.databinding.FragmentOAplikacijiBinding

class OAplikaciji : Fragment() {
    lateinit var binding: FragmentOAplikacijiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_o_aplikaciji, container, false)


        return binding.root
    }

}