package xyz.alexie.basicapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.alexie.basicapp.domain.PopularRestaurantEntity

@Dao
abstract class PopularRestaurantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopularRestaurants(restaurants: List<PopularRestaurantEntity>)

    @Query("SELECT * FROM popular_rest_entity")
    abstract fun getPopularRestaurants(): List<PopularRestaurantEntity>
}