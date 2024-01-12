package com.recetasyape.app.utils.extension_functions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.hideAndAddFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    val currentFragment = findFragmentById(containerId)
    if (currentFragment != null) {
        val transaction = beginTransaction()
        transaction.hide(currentFragment)
        transaction.add(containerId, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}