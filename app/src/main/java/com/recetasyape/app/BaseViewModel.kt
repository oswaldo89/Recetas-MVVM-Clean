package com.recetasyape.app


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recetasyape.app.utils.OneTimeEvent
import com.recetasyape.app.utils.extension_functions.asLiveData

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseViewModel<STATE, NAVIGATION> : ViewModel() {

    protected val state: MutableLiveData<STATE> = MutableLiveData()
    val stateLiveData = state.asLiveData()

    protected val navigation: MutableLiveData<OneTimeEvent<NAVIGATION>> = MutableLiveData()
    val navigationLiveData = navigation.asLiveData()

    fun hasViewState() = state.value != null

    fun setState(newState: STATE) {
        newState?.let {
            state.postValue(it)
        }
    }

    fun setValue(newState: STATE) {
        newState?.let {
            state.value = it
        }
    }

    fun navigateTo(newNavigation: NAVIGATION) {
        navigation.postValue(OneTimeEvent(newNavigation))
    }
}
