package com.example.crytocurrencyapp.presentation

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crytocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.example.crytocurrencyapp.presentation.coin_list.CoinListScreen
import com.example.crytocurrencyapp.presentation.ui.theme.CrytoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

// Marks an Android component class to be setup for injection
// OR
// It will allow daggerHilt to inject dependencies into this activity or in sub-composables
// which we need in VM.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrytoCurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.  CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ){
                            CoinListScreen(navController = navController)
                        }
                        composable(
                            // CoinDetailScreen needs CoinID
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ){
                            CoinDetailScreen()
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrytoCurrencyAppTheme {

    }
}