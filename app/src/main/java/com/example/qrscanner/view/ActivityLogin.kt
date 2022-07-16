package com.example.qrscanner.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qrscanner.DataStore
import com.example.qrscanner.R
import com.example.qrscanner.databinding.ActivityLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class ActivityLogin : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var  _binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navGraph = navController.navInflater.inflate(R.navigation.main_nav)

        navController.graph = navGraph


        _binding.bottomNavigation.apply {
            setupWithNavController(navController = navController)
        }


    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.loginFragment ||
                destination.id == R.id.classFragment ||
                destination.id == R.id.qrFragment)  {
                _binding.bottomNavigation.visibility  = View.GONE
            } else {
                _binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        lifecycleScope.launch {
            if (!DataStore.fetch(this@ActivityLogin, "username").equals("")) {
                val bundle = Bundle()
                bundle.putString("result", DataStore.fetch(this@ActivityLogin, "status"))
                Navigation.findNavController(this@ActivityLogin, R.id.nav_host_fragment).navigate(
                    R.id.courseFragment, bundle)
            }

        }
    }

}