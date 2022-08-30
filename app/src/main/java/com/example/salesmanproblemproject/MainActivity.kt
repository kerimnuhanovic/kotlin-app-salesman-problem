package com.example.salesmanproblemproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.salesmanproblemproject.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))


        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.titleFragment2

            )
            .build()

        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment),appBarConfiguration)

        Timber.i("onCreate called!")
        Timber.i("Test istovremenog pusha!")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}