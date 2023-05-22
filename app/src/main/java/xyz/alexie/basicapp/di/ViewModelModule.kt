package xyz.alexie.basicapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.alexie.basicapp.presentation.login.LoginViewModel
import xyz.alexie.basicapp.presentation.restaraunts.RestaurantsViewModel

val viewModelsModule = module {
    viewModel { LoginViewModel() }
    viewModel { RestaurantsViewModel(get()) }
}