package xyz.alexie.basicapp.presentation.restaraunts

import xyz.alexie.basicapp.domain.Restaurant

data class RestaurantsState(
    val loading: Boolean = true,
    val nearestRestaurants: List<Restaurant> = listOf(),
    val popularRestaurants: List<Restaurant> = listOf()
)