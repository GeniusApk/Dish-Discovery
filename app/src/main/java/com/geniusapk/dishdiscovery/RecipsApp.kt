package com.geniusapk.dishdiscovery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState


    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewstate, navigateToDetail = { category ->
                navController.navigate(Screen.DetailScreen.route + "/${category.idCategory}")
            })



        }

        composable(route = Screen.DetailScreen.route + "/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            val category = viewstate.list.find { it.idCategory == categoryId }
            if (category != null) {
                CategoryDetailScreen(category = category)
            }
        }

    }


}