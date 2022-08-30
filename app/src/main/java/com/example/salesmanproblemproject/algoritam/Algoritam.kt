package com.example.salesmanproblemproject.algoritam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesmanproblemproject.database.GradDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlin.Double.Companion.MAX_VALUE

class Algoritam(var lista: List<GradDB>): ViewModel() {

    lateinit var pocetniGrad: GradDB
    private var __minimalanPut = MutableLiveData<Double>()
    val minimalanPut: LiveData<Double>
        get() = __minimalanPut

    var najkraciPut = MutableLiveData<List<GradDB>>()

    private val __algoritamZavrsen = MutableLiveData<Boolean>()
    val algoritamZavrsen: LiveData<Boolean>
        get() = __algoritamZavrsen
    init {
       __minimalanPut.value = MAX_VALUE
        pocetniGrad = lista[0]
        __algoritamZavrsen.value = false
    }

    fun najkraciPutFunkcija() {

        var l = lista.toMutableList()
        l.removeFirst()
        permutacije(l)
        __algoritamZavrsen.value = true

    }

    private fun permutacije(lista : MutableList<GradDB>):MutableList<MutableList<GradDB>> {

        var rezultat = mutableListOf<MutableList<GradDB>>()
        if (lista.size == 1) {
            var listaPomocna: MutableList<MutableList<GradDB>> = mutableListOf(mutableListOf(lista[0]))
            return listaPomocna
        }

        for(i in 0..lista.size-1) {
            var prvi = lista.removeFirst()
            var perm = permutacije(lista)


            for(j in 0..perm.size-1)
                perm[j].add(prvi)
            /*Ubacit ptovjeru da li je duzina niza jednaka*/
            if(perm[0].size == this.lista.size.minus(1)) {
                for(j in 0..perm.size-1) {
                    var trenutniPut = udaljenost(pocetniGrad.latituda,pocetniGrad.longituda,perm[j][0].latituda,
                        perm[j][0].longituda)
                    for(k in 0..perm[j].size-2) {
                        trenutniPut += udaljenost(perm[j][k].latituda,perm[j][k].longituda,
                            perm[j][k+1].latituda,perm[j][k+1].longituda)
                    }
                    trenutniPut += udaljenost(perm[j][perm[j].size-1].latituda,
                        perm[j][perm[j].size-1].longituda,pocetniGrad.latituda,pocetniGrad.longituda)
                    if(trenutniPut < __minimalanPut.value!!) {
                        __minimalanPut.value = trenutniPut
                        var pomocna = perm[j]
                        pomocna.add(0,pocetniGrad!!)
                        najkraciPut.value = pomocna
                    }

                }

            }
            rezultat = rezultat.plus(perm).toMutableList()/*Provjerit da li merga dobro*/
            lista.add(prvi)
        }
        return rezultat
    }

    fun udaljenost(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        dist = dist * 1.609344
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}