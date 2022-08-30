package com.example.salesmanproblemproject

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class PrikaziPut : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prikazi_put, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync {
            val args = PrikaziPutArgs.fromBundle(requireArguments())
            val gradovi = args.lista.lista
            for (i in 1..gradovi.size - 1) {
                val polyline1 = it.addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(gradovi[i - 1].latituda, gradovi[i - 1].longituda),
                            LatLng(gradovi[i].latituda, gradovi[i].longituda)
                        )
                )
            }
            it.addMarker(
                MarkerOptions().position(LatLng(gradovi[0].latituda, gradovi[0].longituda))
                    .title("Početni")
            )

            it.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(
                        gradovi[0].latituda,
                        gradovi[0].longituda
                    )
                )
            )


        }
    }
}