package xyz.alexie.basicapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Catalog(
    val nearest: List<Restaurant>,
    val popular: List<Restaurant>,
    val commercial: Commercial?
)

@Serializable
data class Restaurant(
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class Commercial(
    val picture: String,
    val url: String
)