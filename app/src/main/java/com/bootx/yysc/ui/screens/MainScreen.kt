package com.bootx.yysc.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.SharedPreferencesUtils

data class NavigationItem(
    val title: String, //底部导航栏的标题
    val icon: ImageVector//底部导航栏图标
)

@Composable
fun MainScreen(navController: NavHostController,type: String="0") {
    var context = LocalContext.current
    val navigationItems = listOf(
        NavigationItem(title = "首页", icon = Icons.Filled.Home),
        NavigationItem(title = "应用", icon = Icons.Filled.Apps),
        /*NavigationItem(title = "游戏", icon = Icons.Filled.Games),
        NavigationItem(title = "广场", icon = Icons.Filled.Place),*/
        NavigationItem(title = "我的", icon = Icons.Filled.Person),
    )

    var currentNavigationIndex by remember {
        mutableIntStateOf(type.toInt())
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.navigationBarsPadding()
            ){
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            if(currentNavigationIndex!=index){
                                currentNavigationIndex = index
                                navController.navigate(Destinations.MainFrame.route+"/${index}")
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        selectedContentColor = Color(0xFF149EE7),
                        unselectedContentColor = Color(0xFF999999)
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            when(currentNavigationIndex){
                0-> {
                    SharedPreferencesUtils(context).set("homeIndex","0")
                    HomeScreen(navController = navController)
                }
                1-> {
                    SharedPreferencesUtils(context).set("homeIndex","1")
                    AppScreen(navController = navController)
                }
                2-> {
                    SharedPreferencesUtils(context).set("homeIndex","2")
                    MineScreen(navController = navController)
                }
            }
        }
    }
}


