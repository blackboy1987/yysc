package com.bootx.yysc.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.screens.AppDetail1Screen
import com.bootx.yysc.ui.screens.AppDetailScreen
import com.bootx.yysc.ui.screens.FanScreen
import com.bootx.yysc.ui.screens.FuLiScreen
import com.bootx.yysc.ui.screens.HotScreen
import com.bootx.yysc.ui.screens.ListScreen
import com.bootx.yysc.ui.screens.LoginScreen
import com.bootx.yysc.ui.screens.MainFrame
import com.bootx.yysc.ui.screens.MyIconListScreen
import com.bootx.yysc.ui.screens.MyIconScreen
import com.bootx.yysc.ui.screens.QunZuScreen
import com.bootx.yysc.ui.screens.SearchScreen
import com.bootx.yysc.ui.screens.SettingScreen
import com.bootx.yysc.ui.screens.SignInScreen
import com.bootx.yysc.ui.screens.SupportRankScreen
import com.bootx.yysc.ui.screens.SupportScreen
import com.bootx.yysc.ui.screens.TouGaoAppInfoListScreen
import com.bootx.yysc.ui.screens.TouGaoListScreen
import com.bootx.yysc.ui.screens.TouGaoScreen

@Composable
fun NavHostApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.AppDetail1Frame.route+"/61862",
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

        composable(
            Destinations.QunZuFrame.route,
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
            QunZuScreen(navController)
        }

        composable(
            Destinations.FuLiFrame.route,
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
            FuLiScreen(navController)
        }

        composable(
            Destinations.HotFrame.route,
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
            HotScreen(navController)
        }

        composable(
            Destinations.FanFrame.route,
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
            FanScreen(navController)
        }
        composable(
            Destinations.MyIconFrame.route,
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
            MyIconScreen(navController)
        }
        composable(
            Destinations.MyIconListFrame.route,
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
            MyIconListScreen(navController)
        }
        composable(
            Destinations.TouGaoListFrame.route,
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
            TouGaoListScreen(navController)
        }

        composable(
            Destinations.TouGaoAppInfoListFrame.route,
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
            TouGaoAppInfoListScreen(navController)
        }
        composable(
            Destinations.TouGaoFrame.route+"/{packageName}",
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
            val packageName = it.arguments?.getString("packageName") ?: ""
            TouGaoScreen(navController,packageName)
        }
        composable(
            Destinations.AppDetailFrame.route+"/{id}",
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
            Log.e("NavHostApp", "NavHostApp: ${it.arguments.toString()}", )
            val id = it.arguments?.getString("id") ?: ""
            AppDetailScreen(navController,id)
        }
        composable(
            Destinations.SettingFrame.route,
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
            SettingScreen(navController)
        }
        composable(
            Destinations.AppDetail1Frame.route+"/1234",
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
            Log.e("NavHostApp", "NavHostApp: ${it.arguments.toString()}", )
            val id = it.arguments?.getString("id") ?: ""
            AppDetail1Screen(navController,id)
        }
    }
}