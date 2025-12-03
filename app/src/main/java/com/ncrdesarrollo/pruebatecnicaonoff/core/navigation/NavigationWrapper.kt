package com.ncrdesarrollo.pruebatecnicaonoff.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ncrdesarrollo.pruebatecnicaonoff.comments.ui.CommentsScreen
import com.ncrdesarrollo.pruebatecnicaonoff.comments.ui.CommentsViewModel
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
            val viewModel = hiltViewModel<CommentsViewModel>()
            CommentsScreen(modifier, viewModel) {
                navController.popBackStack()
            }
        }

    }
}