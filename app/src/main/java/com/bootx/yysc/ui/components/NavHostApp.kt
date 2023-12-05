package com.bootx.yysc.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bootx.yysc.model.entity.SysMsgScreen
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.screens.AboutScreen
import com.bootx.yysc.ui.screens.AppDetail1Screen
import com.bootx.yysc.ui.screens.AppDetailScreen
import com.bootx.yysc.ui.screens.ComplaintsScreen
import com.bootx.yysc.ui.screens.FanScreen
import com.bootx.yysc.ui.screens.FuLiScreen
import com.bootx.yysc.ui.screens.HotScreen
import com.bootx.yysc.ui.screens.IconDetailScreen
import com.bootx.yysc.ui.screens.ListScreen
import com.bootx.yysc.ui.screens.LoginScreen
import com.bootx.yysc.ui.screens.MainFrame
import com.bootx.yysc.ui.screens.MyIconListScreen
import com.bootx.yysc.ui.screens.MyIconScreen
import com.bootx.yysc.ui.screens.NotifyScreen
import com.bootx.yysc.ui.screens.QunZuScreen
import com.bootx.yysc.ui.screens.SearchScreen
import com.bootx.yysc.ui.screens.SettingScreen
import com.bootx.yysc.ui.screens.SignInScreen
import com.bootx.yysc.ui.screens.SupportRankScreen
import com.bootx.yysc.ui.screens.SupportScreen
import com.bootx.yysc.ui.screens.TouGaoAppInfoListScreen
import com.bootx.yysc.ui.screens.TouGaoListScreen
import com.bootx.yysc.ui.screens.TouGaoScreen
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.SettingViewModel
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavHostApp(settingViewModel:SettingViewModel= viewModel()) {
    val navController = rememberNavController()
    var context = LocalContext.current
    val storeManager: StoreManager = StoreManager(context)

    LaunchedEffect(Unit){
        val gson = Gson()
        val setting = settingViewModel.initApp(context)
        storeManager.save("setting",gson.toJson(setting))
    }

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
            route = Destinations.ListFrame.route+"/{title}/{orderBy}",
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
            val id = it.arguments?.getString("id") ?: ""
            AppDetail1Screen(navController,id)
        }
        composable(
            Destinations.NotifyFrame.route,
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
            NotifyScreen(navController)
        }
        composable(
            Destinations.SysMsgFrame.route,
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
            SysMsgScreen(navController)
        }
        composable(
            Destinations.IconDetailFrame.route,
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
            IconDetailScreen(navController)
        }
        composable(
            Destinations.AboutFrame.route,
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
            AboutScreen(navController)
        }
        composable(
            Destinations.ComplaintsFrame.route+"/{id}",
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
            val id = it.arguments?.getString("id") ?: ""
            ComplaintsScreen(navController,id)
        }
    }
}