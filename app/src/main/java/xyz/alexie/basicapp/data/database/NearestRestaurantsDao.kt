package xyz.alexie.basicapp.data.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.alexie.basicapp.domain.NearestRestaurantEntity

@Dao
abstract class NearestRestaurantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertNearestRestaurants(restaurants: List<NearestRestaurantEntity>)

    @Query("SELECT * FROM nearest_rest_entity")
    abstract fun getNearestRestaurants(): List<NearestRestaurantEntity>
}