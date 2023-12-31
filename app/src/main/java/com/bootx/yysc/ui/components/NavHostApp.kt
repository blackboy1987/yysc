package com.bootx.yysc.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bootx.yysc.model.entity.SysMsgScreen
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.screens.AboutScreen
import com.bootx.yysc.ui.screens.AppDetailScreen
import com.bootx.yysc.ui.screens.AppMoreScreen
import com.bootx.yysc.ui.screens.AppRankScreen
import com.bootx.yysc.ui.screens.ComplaintsScreen
import com.bootx.yysc.ui.screens.DownloadManagerScreen
import com.bootx.yysc.ui.screens.FanScreen
import com.bootx.yysc.ui.screens.FuLiScreen
import com.bootx.yysc.ui.screens.HistoryScreen
import com.bootx.yysc.ui.screens.HotScreen
import com.bootx.yysc.ui.screens.ListScreen
import com.bootx.yysc.ui.screens.LoginScreen
import com.bootx.yysc.ui.screens.MainScreen
import com.bootx.yysc.ui.screens.MemberScreen
import com.bootx.yysc.ui.screens.MyIconListScreen
import com.bootx.yysc.ui.screens.MyIconScreen
import com.bootx.yysc.ui.screens.NotifyListScreen
import com.bootx.yysc.ui.screens.NotifyScreen
import com.bootx.yysc.ui.screens.OtherScreen
import com.bootx.yysc.ui.screens.QunZuScreen
import com.bootx.yysc.ui.screens.SearchScreen
import com.bootx.yysc.ui.screens.SettingScreen
import com.bootx.yysc.ui.screens.SignInScreen
import com.bootx.yysc.ui.screens.SupportRankScreen
import com.bootx.yysc.ui.screens.SupportScreen
import com.bootx.yysc.ui.screens.TestScreen
import com.bootx.yysc.ui.screens.TouGaoAppInfoListScreen
import com.bootx.yysc.ui.screens.TouGaoListScreen
import com.bootx.yysc.ui.screens.TouGaoScreen
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.SettingViewModel
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavHostApp(settingViewModel: SettingViewModel = viewModel()) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val storeManager: StoreManager = StoreManager(context)

    LaunchedEffect(Unit) {
        val gson = Gson()
        val setting = settingViewModel.initApp(context)
        storeManager.save("setting", gson.toJson(setting))
    }
    NavHost(
        navController = navController,
        startDestination = Destinations.MainFrame.route+"/0",
    ) {
        composable(
            Destinations.MainFrame.route + "/{type}",
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
            val type = it.arguments?.getString("type") ?: "0"
            MainScreen(navController, type)
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
            route = Destinations.ListFrame.route + "/{title}/{orderBy}",
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
            ListScreen(navController, title, orderBy)
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
            Destinations.FanFrame.route + "/{type}",
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
            val type = it.arguments?.getInt("type") ?: 0
            FanScreen(navController, type)
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
            Destinations.TouGaoFrame.route + "/{packageName}",
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
            TouGaoScreen(navController, packageName)
        }
        composable(
            Destinations.AppDetailFrame.route + "/{id}",
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
            Log.e("NavHostApp", "NavHostApp: ${it.arguments.toString()}")
            val id = it.arguments?.getString("id") ?: ""
            AppDetailScreen(navController, id)
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
            Destinations.NotifyListFrame.route + "/{type}",
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
            val type = it.arguments?.getString("type") ?: "0"
            NotifyListScreen(navController, type)
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
            Destinations.ComplaintsFrame.route + "/{id}",
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
            ComplaintsScreen(navController, id)
        }
        composable(
            Destinations.AppMoreFrame.route + "/{id}",
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
            AppMoreScreen(navController, id)
        }

        composable(
            Destinations.DownloadManagerFrame.route,
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
            DownloadManagerScreen(navController)
        }
        composable(
            Destinations.HistoryFrame.route,
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
            HistoryScreen(navController)
        }
        composable(
            Destinations.MemberFrame.route + "/{id}",
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
            MemberScreen(navController, id)
        }
        composable(
            Destinations.AppRankFrame.route,
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
            AppRankScreen(navController)
        }





        composable(
            Destinations.TestFrame.route,
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
            TestScreen(navController)
        }
        composable(
            Destinations.OtherFrame.route,
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
            OtherScreen(navController)
        }
    }
}