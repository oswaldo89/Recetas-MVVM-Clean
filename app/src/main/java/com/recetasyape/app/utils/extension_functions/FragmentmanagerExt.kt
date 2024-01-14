package com.recetasyape.app.utils.extension_functions

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.recetasyape.app.MainActivity.Companion.DATA_BUNDLE
import com.recetasyape.app.R

fun FragmentManager.hideAndAddFragment(containerId: Int, fragment: Fragment, tag: String? = null, dataObject: Parcelable? = null) {
    val currentFragment = findFragmentById(containerId)
    if (currentFragment != null) {
        val transaction = beginTransaction()

        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)

        transaction.hide(currentFragment)
        transaction.add(containerId, fragment, tag)

        if (dataObject != null) {
            val bundle = Bundle()
            bundle.putParcelable(DATA_BUNDLE, dataObject)
            fragment.arguments = bundle
        }

        transaction.addToBackStack(null)
        transaction.commit()
    }
}