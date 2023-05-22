package xyz.alexie.basicapp.presentation.restaraunts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import xyz.alexie.basicapp.domain.Restaurant
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun RestaurantsScreen(
    navController: NavController
) {
    val viewModel: RestaurantsViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Restaurants(
        loading = state.loading,
        popular = state.popularRestaurants,
        nearest = state.nearestRestaurants,
        onDetailClick = { name, deliveryTime, imagePath ->
            navController.navigate("RestaurantDetail/$name/$deliveryTime/$imagePath")
        }
    )
}

@Composable
private fun Restaurants(
    loading: Boolean,
    popular: List<Restaurant>,
    nearest: List<Restaurant>,
    onDetailClick: (name: String, deliveryTime: String, imagePath: String) -> Unit,
) {
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 12.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Find Your Favorite Food",
                    style = TextStyle(
                        fontSize = 18.sp, color = Color(0xFF09051C), fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            }
            item(span = { GridItemSpan(2) }) {
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                    text = "Popular Restaurant",
                    style = TextStyle(
                        fontSize = 18.sp, color = Color(0xFF09051C), fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            }
            items(popular) {
                RestaurantItem(
                    modifier = Modifier.clickable {
                        val encodedUrl =
                            URLEncoder.encode(it.image, StandardCharsets.UTF_8.toString())
                        onDetailClick(
                            it.name,
                            it.deliveryTime,
                            encodedUrl
                        )
                    },
                    name = it.name,
                    deliveryTime = it.deliveryTime,
                    imagePath = it.image
                )
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                    text = "Nearest Restaurant",
                    style = TextStyle(
                        fontSize = 18.sp, color = Color(0xFF09051C), fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            }
            items(nearest) {
                RestaurantItem(
                    modifier = Modifier.clickable {
                        val encodedUrl =
                            URLEncoder.encode(it.image, StandardCharsets.UTF_8.toString())
                        onDetailClick(
                            it.name,
                            it.deliveryTime,
                            encodedUrl
                        )
                    },
                    name = it.name,
                    deliveryTime = it.deliveryTime,
                    imagePath = it.image
                )
            }
        }
    }
}

@Composable
fun RestaurantItem(
    name: String,
    deliveryTime: String,
    imagePath: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(180.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(top = 26.dp)
                    .size(90.dp)
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imagePath)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = name,
                style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black
                )
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
                text = deliveryTime,
                style = TextStyle(
                    fontSize = 13.sp, color = Color.Gray
                )
            )
        }
    }
}