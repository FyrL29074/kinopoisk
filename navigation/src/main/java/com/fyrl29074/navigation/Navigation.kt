package com.fyrl29074.navigation

import androidx.fragment.app.Fragment

interface Navigation {
    fun navigateToMoviePage(fragment: Fragment, screen: NavScreens, id: Int)
}