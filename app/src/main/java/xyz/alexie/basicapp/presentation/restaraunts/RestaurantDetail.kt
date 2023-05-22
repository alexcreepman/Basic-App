package xyz.alexie.basicapp.presentation.restaraunts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun RestaurantDetail(
    name: String,
    deliveryTime: String,
    imagePath: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val decodedUrl = URLDecoder.decode(imagePath,StandardCharsets.UTF_8.toString())
        RestaurantItem(
            modifier = Modifier.align(Alignment.Center),
            name = name,
            deliveryTime = deliveryTime,
            imagePath = decodedUrl
        )
    }
}