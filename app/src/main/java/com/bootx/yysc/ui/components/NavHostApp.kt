package com.bootx.yysc.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.screens.ListScreen
import com.bootx.yysc.ui.screens.LoginScreen
import com.bootx.yysc.ui.screens.MainFrame
import com.bootx.yysc.ui.screens.SearchScreen
import com.bootx.yysc.ui.screens.SignInScreen
import com.bootx.yysc.ui.screens.SupportRankScreen
import com.bootx.yysc.ui.screens.SupportScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.HomeFrame.route,
    ) {
        composable(
            Destinations.HomeFrame.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            MainFrame(navController)
        }
        composable(
            Destinations.LoginFrame.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            LoginScreen(navController)
        }
        composable(
            Destinations.SearchFrame.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            SearchScreen(navController)
        }
        composable(
            route = "ListFrame/{title}/{orderBy}",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            val title = it.arguments?.getString("title") ?: ""
            val orderBy = it.arguments?.getString("orderBy") ?: ""
            ListScreen(navController,title,orderBy)
        }
        composable(
            Destinations.SupportFrame.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            SupportScreen(navController)
        }

        composable(
            Destinations.SupportRankFrame.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            SupportRankScreen(navController)
        }

        composable(
            Destinations.SignInFrame.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            SignInScreen(navController)
        }
    }
}