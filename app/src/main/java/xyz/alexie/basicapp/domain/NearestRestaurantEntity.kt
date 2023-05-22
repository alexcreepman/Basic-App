package xyz.alexie.basicapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nearest_rest_entity")
data class NearestRestaurantEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

fun Restaurant.toNearestRestaurantEntity() =
    NearestRestaurantEntity(
        id = this.id,
        deliveryTime = this.deliveryTime,
        name = this.name,
        image = this.image
    )

fun NearestRestaurantEntity.toRestaurant() =
    Restaurant(
        id = this.id,
        deliveryTime = this.deliveryTime,
        name = this.name,
        image = this.image
    )
