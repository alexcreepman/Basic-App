package xyz.alexie.basicapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_rest_entity")
data class PopularRestaurantEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

fun Restaurant.toPopularRestaurantEntity() =
    PopularRestaurantEntity(
        id = this.id,
        deliveryTime = this.deliveryTime,
        name = this.name,
        image = this.image
    )

fun PopularRestaurantEntity.toRestaurant() =
    Restaurant(
        id = this.id,
        deliveryTime = this.deliveryTime,
        name = this.name,
        image = this.image
    )