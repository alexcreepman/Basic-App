package xyz.alexie.basicapp.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module
import xyz.alexie.basicapp.data.RestaurantRepository

val repositoryModule = module {
    single { RestaurantRepository(get(), get(), get()) }
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}