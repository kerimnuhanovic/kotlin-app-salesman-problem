package com.example.salesmanproblemproject

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanproblemproject.database.GradDBViewModel
import com.example.salesmanproblemproject.databinding.FragmentListaGradovaBinding
import com.example.salesmanproblemproject.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ListaGradovaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaGradovaFragment : Fragment() {
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: FragmentListaGradovaBinding
    private lateinit var gradViewModel: GradDBViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListaGradovaBinding.inflate(inflater, container, false)

        //postavljanje recyclerView-a
        val grad_adapter = GradAdapter()
        val recyclerView: RecyclerView = binding.recycleGradovi
        recyclerView.adapter = grad_adapter//kraj postavljanja recycler Viewa

        /*dodat liniju koda ako zatreba*/
        gradViewModel = ViewModelProvider(this).get(GradDBViewModel::class.java)

        //u fragment
        setHasOptionsMenu(true)
        var drawerLayout: DrawerLayout = binding.drawerLayout
        var navView = binding.navView
        toggle = ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()///kraj za bocni meni
        gradViewModel.readAllData.observe(viewLifecycleOwner, Observer { grad ->
            grad_adapter.setGradovi(grad)
        })//u fragment

        navView.bringToFront();

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1-> navView.findNavController().navigate(R.id.action_listaGradovaFragment_to_OAplikaciji)
            }
            true
        }


        binding.floatingActionButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listaGradovaFragment_to_dodajGradFragment)
        )

        binding.izbrisiSve.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("DA") { _, _ ->
                gradViewModel.deleteAll()
                Toast.makeText(requireContext(), "Gradovi izbrisani", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("NE") { _, _ ->

            }
            builder.setTitle("Izbrisi")
            builder.setMessage("Da li želite izbrisati sve gradove?")
            builder.show()
        }
        ///////////pocetak za bocni meni
        binding.dugme.setOnClickListener {
            var listaPomocna: ListaGradova = ListaGradova(gradViewModel.readAllData.value!!)
            if (gradViewModel.readAllData.value!!.size == 0 || gradViewModel.readAllData.value!!.size == 1 ||
                gradViewModel.readAllData.value!!.size == 2
            ) {
                Toast.makeText(requireContext(), "Potrebna su barem tri grada za računanje puta!", Toast.LENGTH_LONG).show()
            } else {
                it.findNavController().navigate(
                    ListaGradovaFragmentDirections.actionListaGradovaFragmentToNajkraciPutFragment(
                        listaPomocna
                    )
                )

            }
        }

        return binding.root;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}