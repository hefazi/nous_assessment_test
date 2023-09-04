package com.example.assessmenttest.compose

import EmailViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assessmenttest.module.ImageListViewModel
import com.example.assessmenttest.module.compose.ImageDetailsView

@Composable
fun AssessmentTestApp(viewModel: ImageListViewModel, emailViewModel: EmailViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "item_list") {
        composable("item_list") {
            MainScreen(navController, viewModel = viewModel)
        }

        composable(
            "item_details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            ImageDetailsView(
                viewModel,
                backStackEntry.arguments?.getLong("id"),
            ) { imageModel ->
                if (imageModel != null) {
                    val subject = imageModel.title
                    val body = imageModel.description
                    emailViewModel.createEmailIntent(subject, body, imageModel.imageUrl)
                }
            }
        }
    }
}