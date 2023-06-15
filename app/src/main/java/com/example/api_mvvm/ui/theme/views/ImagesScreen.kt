package com.example.api_mvvm.ui.theme.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.api_mvvm.R
import com.example.api_mvvm.data.LoremPicsum

@Composable
fun ImagesScreen(
    imagesViewModel: ImagesViewModel = viewModel()
) {
    val uiState by imagesViewModel.uiState.collectAsState()
    when(uiState){
        is ImagesUiState.Loading -> LoadingScreen()
        is ImagesUiState.Success -> ImageList((uiState as ImagesUiState.Success).images)
        is ImagesUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {
    Text(text = "Loading")
}

@Composable
fun ErrorScreen() {
    Text(text = "Error")
}

@Composable
fun ImageList(
    images: List<LoremPicsum>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        columns = GridCells.Fixed(1)
    ){
        items(images) { image ->
            ImageEntry(image = image)
        }
    }
}

@Composable
fun ImageEntry(
    image: LoremPicsum
) {
    androidx.compose.material.Card(
        modifier = Modifier.padding(6.dp),
        //elevation = CardDefaults.cardElevation(6.dp) // Não funcionou essa merda
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder), // imagem placeholder
            contentDescription = image.author,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
        )

    }
}