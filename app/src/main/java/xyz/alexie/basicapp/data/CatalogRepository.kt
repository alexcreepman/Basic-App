package xyz.alexie.basicapp.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import xyz.alexie.basicapp.data.database.NearestRestaurantsDao
import xyz.alexie.basicapp.data.database.PopularRestaurantsDao
import xyz.alexie.basicapp.domain.*

class RestaurantRepository(
    private val httpClient: HttpClient,
    private val nearestRestaurantsDao: NearestRestaurantsDao,
    private val popularRestaurantsDao: PopularRestaurantsDao,
) {
    suspend fun fetchCatalog(): Catalog {
        try {
            val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                method = HttpMethod.Get
            }.body<Catalog>()
            nearestRestaurantsDao.insertNearestRestaurants(response.nearest.map(Restaurant::toNearestRestaurantEntity))
            popularRestaurantsDao.insertPopularRestaurants(response.popular.map(Restaurant::toPopularRestaurantEntity))
            return response
        } catch (e: Exception) {
            val nearestRest = nearestRestaurantsDao.getNearestRestaurants()
            val popularRest = popularRestaurantsDao.getPopularRestaurants()
            return Catalog(
                nearest = nearestRest.map(NearestRestaurantEntity::toRestaurant),
                popular = popularRest.map(PopularRestaurantEntity::toRestaurant),
                commercial = null
            )
        }
    }
}