package com.example.api_mvvm.ui.theme.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.api_mvvm.data.Card
import com.example.api_mvvm.network.BASE_URL

@Composable
fun CardsScreen(
    cardsViewModel: CardsViewModel = viewModel()
) {

    val uiState by cardsViewModel.uiState.collectAsState()
    when(uiState){
        is CardsUiState.Loading -> LoadingScreen()
        is CardsUiState.Success -> CardsList((uiState as CardsUiState.Success).cards)
        is CardsUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {

}

@Composable
fun ErrorScreen() {

}

@Composable
fun CardsList(
    cards: List<Card>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        columns = GridCells.Fixed(1)
    ){
        items(cards) {card ->
            CardEntry(card = card)
        }
    }
}

@Composable
fun CardEntry(
    card: Card
) {
    androidx.compose.material.Card(
        modifier = Modifier.padding(6.dp),
        //elevation = CardDefaults.cardElevation(6.dp) não funcionou essa merda
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(card.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.cardback), // imagem placeholder
            contentDescription = card.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
        )

    }
}