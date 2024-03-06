package com.geniusapk.dishdiscovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


// Composable function for displaying the recipe screen

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Category) ->Unit,
    viewState:MainViewModel.RecipeState
){
    //val recipeViewModel: MainViewModel = viewModel()
    //val viewState by recipeViewModel.categoriesState
    // Display different components based on the state

    Box(modifier = Modifier.fillMaxSize()) {
        when{
            viewState.loading ->{
                // Show loading indicator if data is being fetched

                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null ->{
                // Show error message if there is an error

                Text("ERROR OCCURRED")
            }
            else ->{
                // Show categories if data is loaded successfully

                CategoryScreen(categories = viewState.list , navigateToDetail)
            }
        }

    }

}

// Composable function for displaying categories


@Composable
fun CategoryScreen(categories: List<Category> , navigateToDetail: (Category) ->Unit) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories){
            category ->
            // Display each category item

            CategoryItem(category = category, navigateToDetail)

        }
    }
}

// Composable function for displaying a single category item

@Composable
fun CategoryItem(category: Category, navigateToDetail: (Category) -> Unit) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable { navigateToDetail(category) } // Pass the category parameter here
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )

            Text(
                text = category.strCategory,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
