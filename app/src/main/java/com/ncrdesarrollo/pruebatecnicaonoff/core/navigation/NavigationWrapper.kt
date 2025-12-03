package com.ncrdesarrollo.pruebatecnicaonoff.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.PublicationsScreen
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.PublicationsViewModel


@Composable
fun NavigationWrapper(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {

        composable<Home> {
            val viewModel = hiltViewModel<PublicationsViewModel>()
            PublicationsScreen(modifier, viewModel) { idPublication ->
                navController.navigate(CommentsView(idPublication))
            }
        }

        composable<CommentsView> {

        }

    }
}