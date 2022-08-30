package com.example.salesmanproblemproject

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.salesmanproblemproject.database.GradDBViewModel
import com.example.salesmanproblemproject.databinding.FragmentGradDetaljiBinding

class GradDetaljiFragment : Fragment() {

    lateinit var gradViewModel:GradDBViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentGradDetaljiBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_grad_detalji,container,false)

        var args = GradDetaljiFragmentArgs.fromBundle(requireArguments())
        setHasOptionsMenu(true)


        binding.gradNaziv.text = args.grad.grad
        binding.drzavaNaziv.text = args.grad.drzava
        //nesto
        binding.opisTekst?.text = args.grad.opis
        binding.latitudaVrijednost.text = args.grad.latituda.toString()
        binding.longitudaVrijednost.text = args.grad.longituda.toString()


        gradViewModel = ViewModelProvider(this).get(GradDBViewModel::class.java)

        binding.izbrisiGrad.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setPositiveButton("DA") {_,_ ->
                gradViewModel.deleteCity(args.grad)
                it.findNavController().navigate(GradDetaljiFragmentDirections.actionGradDetaljiFragmentToListaGradovaFragment())
            }
            alert.setNegativeButton("NE") {_,_ ->

            }
            alert.setTitle("Izbrisi ${args.grad.grad}?")
            alert.setMessage("Želite li izbrisati grad ${args.grad.grad}")
            alert.create().show()
        }
        binding.mapaDugme.setOnClickListener {
            it.findNavController().navigate(GradDetaljiFragmentDirections.actionGradDetaljiFragmentToPrikazGrada(args.grad.latituda.toString(),args.grad.longituda.toString()))
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sharing_menu, menu)
    }
    private fun getShareIntent() : Intent {
        var args = GradDetaljiFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, args.grad.latituda.toString().plus("-").plus(args.grad.longituda.toString()))
        return shareIntent
    }
    private fun share() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.share -> share()
        }
        return super.onOptionsItemSelected(item)
    }
}