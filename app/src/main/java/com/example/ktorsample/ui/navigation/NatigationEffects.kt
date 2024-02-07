package com.example.ktorsample.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.net.URLEncoder

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val context = (LocalContext.current as? Activity)

    LaunchedEffect(context, navHostController, navigationChannel) {

        navigationChannel.receiveAsFlow().collect { intent ->
            if (context?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {

                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    val route = if (intent.extra.isNotEmpty()) {
                        val extra = URLEncoder.encode(intent.extra, "UTF-8")
                        intent.route + "/" + extra
                    } else {
                        intent.route
                    }

                    navHostController.navigate(route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }

                }
            }
        }
    }
}