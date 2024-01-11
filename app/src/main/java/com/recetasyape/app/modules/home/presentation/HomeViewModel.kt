package com.recetasyape.app.modules.home.presentation

import android.util.Log
import com.recetasyape.app.BaseViewModel
import com.recetasyape.app.core.di.IoDispatcher
import com.recetasyape.app.modules.home.data.dto.Category
import com.recetasyape.app.modules.home.domain.usecases.GetCategoriesUseCase
import com.recetasyape.app.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeViewModel.State.ShowCategories, Unit>() {

    private lateinit var categoriesList: List<Category>

    fun init() {
        launch(
            onError = {
                Log.v("HomeViewModel", "error: ${it.message}")
            },
            block = {
                categoriesList = getCategories()
                setState(State.ShowCategories(getCategories()))
            },
            dispatcher = dispatcher
        )
    }

    sealed class State {
        class ShowCategories(val categories: List<Category>) : State()
    }
}