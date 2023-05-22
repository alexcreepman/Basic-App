package xyz.alexie.basicapp.presentation.restaraunts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import xyz.alexie.basicapp.data.RestaurantRepository

class RestaurantsViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RestaurantsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            init()
        }
    }

    private suspend fun init() {
        val items = repository.fetchCatalog()
        _state.update {
            it.copy(
                loading = false,
                nearestRestaurants = items.nearest,
                popularRestaurants = items.popular
            )
        }
    }
}