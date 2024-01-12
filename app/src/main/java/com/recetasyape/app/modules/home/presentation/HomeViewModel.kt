package com.recetasyape.app.modules.home.presentation

import androidx.lifecycle.viewModelScope
import com.recetasyape.app.BaseViewModel
import com.recetasyape.app.core.di.IoDispatcher
import com.recetasyape.app.modules.home.data.dto.Category
import com.recetasyape.app.modules.home.data.dto.Recipe
import com.recetasyape.app.modules.home.domain.usecases.GetCategoriesUseCase
import com.recetasyape.app.utils.launchSafe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    @IoDispatcher private var dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeViewModel.State.ShowCategories, HomeViewModel.Navigation>() {

    private lateinit var categoriesList: List<Category>

    fun init() {
        viewModelScope.launchSafe {
            categoriesList = getCategories()
            setState(State.ShowCategories(getCategories()))
        }
    }

    fun onRecipeClicked(recipe: Recipe) {
        navigateTo(Navigation.GoToDetail(recipe))
    }

    sealed class State {
        class ShowCategories(val categories: List<Category>) : State()
    }

    sealed class Navigation {
        class GoToDetail(val recipe: Recipe) : Navigation()
    }
}