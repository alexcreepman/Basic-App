package xyz.alexie.basicapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.alexie.basicapp.domain.NearestRestaurantEntity
import xyz.alexie.basicapp.domain.PopularRestaurantEntity

@Database(
    entities = [PopularRestaurantEntity::class, NearestRestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantsDatabase : RoomDatabase() {
    abstract fun getPopularRestaurantsDao(): PopularRestaurantsDao
    abstract fun getNearestRestaurantsDao(): NearestRestaurantsDao
}