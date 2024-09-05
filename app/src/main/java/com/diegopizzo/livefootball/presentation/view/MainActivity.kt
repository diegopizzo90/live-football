package com.diegopizzo.livefootball.presentation.view

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.livefootball.presentation.navigation.Destination
import com.diegopizzo.livefootball.presentation.navigation.NavHost
import com.diegopizzo.livefootball.presentation.navigation.NavigationIntent
import com.diegopizzo.livefootball.presentation.navigation.composable
import com.diegopizzo.livefootball.presentation.viewmodel.MainViewModel
import com.diegopizzo.match.presentation.view.MatchScreen
import com.diegopizzo.match.presentation.viewmodel.MatchViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val matchViewModel: MatchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        setContent {
            LFTheme {
                SetupNavGraph(mainViewModel, matchViewModel)
            }
        }

        mainViewModel.startFetchingLeagues()
    }
}

@Composable
fun SetupNavGraph(
    mainViewModel: MainViewModel,
    matchViewModel: MatchViewModel,
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController,
    )
    NavHost(
        navController = navController,
        startDestination = Destination.Splash,
    ) {
        composable(destination = Destination.Splash) {
            SplashScreen(mainViewModel)
        }
        composable(destination = Destination.Home) {
            MatchScreen(
                viewModel = matchViewModel,
            )
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController,
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
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
                    navHostController.navigate(intent.route) {
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
