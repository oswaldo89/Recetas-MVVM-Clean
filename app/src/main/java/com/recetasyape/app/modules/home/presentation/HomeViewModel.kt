package com.recetasyape.app.modules.home.presentation

import androidx.lifecycle.viewModelScope
import com.recetasyape.app.BaseViewModel
import com.recetasyape.app.core.di.IoDispatcher
import com.recetasyape.app.modules.home.domain.model.Category
import com.recetasyape.app.modules.home.domain.model.Recipe
import com.recetasyape.app.modules.home.domain.usecases.GetCategoriesUseCase
import com.recetasyape.app.modules.home.domain.usecases.GetRecipesByQueryFilterUseCase
import com.recetasyape.app.utils.launchSafe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    private val getRecipesByQueryFilter: GetRecipesByQueryFilterUseCase,
) : BaseViewModel<HomeViewModel.State, HomeViewModel.Navigation>() {

    private lateinit var categoriesList: List<Category>

    fun init() {
        viewModelScope.launchSafe {
            setValue(State.Loading(true))
            categoriesList = getCategories().categories
            setValue(State.ShowCategories(getCategories().categories))
            setValue(State.Loading(false))
        }
    }

    fun onRecipeClicked(recipe: Recipe) {
        navigateTo(Navigation.GoToDetail(recipe))
    }

    fun onSearchTyped(query: String) {
        val filteredRecipes = getRecipesByQueryFilter(categoriesList, query)
        setState(State.SwapSuggestions(filteredRecipes))
    }

    sealed class State {
        class Loading(val loading: Boolean) : State()
        class ShowCategories(val categories: List<Category>) : State()
        class SwapSuggestions(val recipes : List<Recipe>) : State()
    }

    sealed class Navigation {
        class GoToDetail(val recipe: Recipe) : Navigation()
    }
}