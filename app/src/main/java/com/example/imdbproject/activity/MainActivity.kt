package com.example.imdbproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.imdbproject.fragment.MoviesFragment
import com.example.imdbproject.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        setupNavBar()
        mainPresenter = MainPresenter(mainPresenterInterface)
    }

    private fun setupNavBar() {
        val navController = findNavController(R.id.mainNavHostFragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        mainToolbar.setupWithNavController(navController, appBarConfiguration)
    }

    val mainPresenterInterface = object: MainPresenter.MainPresenterInterface {
        override fun onResume() {

        }

    }

    override fun onResume() {
        super.onResume()
        mainPresenter.notifyPresenterOfOnResume()
    }
}
