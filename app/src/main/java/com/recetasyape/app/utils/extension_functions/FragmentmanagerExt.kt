package com.recetasyape.app.utils.extension_functions

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.hideAndAddFragment(containerId: Int, fragment: Fragment, tag: String? = null, dataObject: Parcelable? = null) {
    val currentFragment = findFragmentById(containerId)
    if (currentFragment != null) {
        val transaction = beginTransaction()
        transaction.hide(currentFragment)
        transaction.add(containerId, fragment, tag)

        if (dataObject != null) {
            val bundle = Bundle()
            bundle.putParcelable("data", dataObject)
            fragment.arguments = bundle
        }

        transaction.addToBackStack(null)
        transaction.commit()
    }
}