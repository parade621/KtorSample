package com.example.ktorsample.ui.screens

import android.view.SearchEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ktorsample.ui.navigation.NavigationEffects
import com.example.ktorsample.ui.navigation.Screens
import timber.log.Timber

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()

//    NavigationEffects(
//        navigationChannel = rootViewModel.navigationChannel,
//        navHostController = navController
//    )
//
//    NavHost(navController = navController, startDestination = Screens.SearchScreen.route) {
//
//        composable(
//            route = Screens.HomeScreen.route
//        ) {
//            val viewModel: SearchViewModel = hiltViewModel()
//            val state = viewModel.state.collectAsStateWithLifecycle().value
//            val lazyPagingItems = viewModel.searchPagingResult.collectAsLazyPagingItems()
//
//            Timber.e("recomposition occured!!!!")
//            // 여기서 그럼 ViewModel 리컴포지션 될 때마다 DB에서 데이터를 map으로 데이터 비교해서 UI 갱신해주기
//            viewModel.onEvent(SearchEvent.GetBookmarkedPhotoId)
//            SearchScreen(
//                state = state,
//                onEvent = viewModel::onEvent,
//                lazyPagingItems = lazyPagingItems
//            )
//        }
//    }
}



