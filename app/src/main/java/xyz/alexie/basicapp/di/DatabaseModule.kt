package xyz.alexie.basicapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import xyz.alexie.basicapp.data.database.RestaurantsDatabase

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            RestaurantsDatabase::class.java,
            "Database"
        ).fallbackToDestructiveMigration().build()
    }
    single { get<RestaurantsDatabase>().getPopularRestaurantsDao() }
    single { get<RestaurantsDatabase>().getNearestRestaurantsDao() }
}