package com.example.imdbproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdbproject.fragment.MoviesFragment
import com.example.imdbproject.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        mainPresenter = MainPresenter(mainPresenterInterface)
        //setupFragment()
    }

    val mainPresenterInterface = object: MainPresenter.MainPresenterInterface {
        override fun onResume() {

        }

    }

    private fun setupFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainNavHostFragment, MoviesFragment())
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.notifyPresenterOfOnResume()
    }
}
