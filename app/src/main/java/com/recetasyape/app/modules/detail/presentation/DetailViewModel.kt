package com.recetasyape.app.modules.detail.presentation

import com.recetasyape.app.BaseViewModel
import com.recetasyape.app.modules.home.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : BaseViewModel<DetailViewModel.State, DetailViewModel.Navigation>() {

    fun onLoadRecipe(recipe: Recipe) {
        setState(State.LoadRecipe(recipe))
    }

    fun onLoadMap(recipe: Recipe) {
        navigateTo(Navigation.GoToMap(recipe))
    }

    fun onImageClicked(url: String) {
        navigateTo(Navigation.ImageViewer(url))
    }

    fun onShareClicked(recipe: Recipe) {
        navigateTo(Navigation.ShareSocialNetworks(recipe))
    }

    sealed class State {
        class LoadRecipe(val recipe: Recipe) : State()
    }

    sealed class Navigation {
        class GoToMap(val recipe: Recipe) : Navigation()
        class ImageViewer(val url: String) : Navigation()
        class ShareSocialNetworks(val recipe: Recipe) : Navigation()
    }
}