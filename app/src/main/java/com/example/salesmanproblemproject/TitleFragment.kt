package com.example.salesmanproblemproject

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.salesmanproblemproject.databinding.FragmentTitleBinding
import timber.log.Timber

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        /*binding.dugmeSljedeci.setOnClickListener {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragment2ToListaGradovaFragment())

        }*/
        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Timber.i("Preostalo" + millisUntilFinished)}

            override fun onFinish() {
                activity?.findNavController( R.id.myNavHostFragment)?.navigate(R.id.action_titleFragment2_to_listaGradovaFragment2 )
            }
        }.start()



        return binding.root;
    }


}