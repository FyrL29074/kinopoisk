package com.fyrl29074.kinopoisk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fyrl29074.movieslist.presentation.ui.MovieListFragmentDirections
import com.fyrl29074.navigation.NavScreens
import com.fyrl29074.navigation.Navigation

class MainActivity : AppCompatActivity(), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToMoviePage(fragment: Fragment, screen: NavScreens, id: Int) {
        val action = MovieListFragmentDirections.toMoviePage(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
}