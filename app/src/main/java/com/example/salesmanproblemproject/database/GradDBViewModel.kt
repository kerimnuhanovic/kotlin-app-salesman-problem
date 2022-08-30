package com.example.salesmanproblemproject.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.salesmanproblemproject.GradRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

public class GradDBViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<GradDB>>
    private val repository: GradRepository

    init {
        val gradDao = Baza.getDatabase(application).gradDao
        repository = GradRepository(gradDao)
        readAllData = repository.readAllData
    }

    fun addGrad(grad: GradDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGrad(grad)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun deleteCity(grad:GradDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCity(grad)
        }
    }

    fun dajSveGradove():List<GradDB> {
        return repository.dajSveGradove()
    }
/*
    private suspend fun pomocna():List<GradDB> {
        delay(3000)
        return repository.dajSveGradove()
    }
*/
}