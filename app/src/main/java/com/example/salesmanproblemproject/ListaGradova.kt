package com.example.salesmanproblemproject

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.example.salesmanproblemproject.database.GradDB
import kotlinx.android.parcel.Parcelize

@Parcelize
class ListaGradova(var lista: List<GradDB>): Parcelable {

}