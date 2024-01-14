package com.recetasyape.app.modules.home.presentation

import androidx.lifecycle.viewModelScope
import com.recetasyape.app.BaseViewModel
import com.recetasyape.app.core.di.IoDispatcher
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.modules.home.domain.usecases.GetCategoriesUseCase
import com.recetasyape.app.utils.launchSafe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeViewModel.State, HomeViewModel.Navigation>() {

    private lateinit var categoriesList: List<Category>

    fun init() {
        viewModelScope.launchSafe {
            setState(State.Loading(true))
            categoriesList = getCategories().categories
            setState(State.ShowCategories(getCategories().categories))
            setState(State.Loading(false))
        }
    }

    fun onRecipeClicked(recipe: Recipe) {
        navigateTo(Navigation.GoToDetail(recipe))
    }

    sealed class State {
        class Loading(val loading: Boolean) : State()
        class ShowCategories(val categories: List<Category>) : State()
    }

    sealed class Navigation {
        class GoToDetail(val recipe: Recipe) : Navigation()
    }
}