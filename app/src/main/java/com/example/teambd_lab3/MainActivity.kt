package com.example.teambd_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dbchangedatafeature.Views.DBChangeDataRoot
import com.example.dbselectionfeature.Views.DBSelectionRoot
import com.example.interactionformfeature.Views.InteractionFormRoot
import com.example.teambd_lab3.ui.theme.TeamBd_lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamBd_lab3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    AppRoot()
                }
            }
        }
    }
}

@Composable
fun AppRoot(){
    val navController = rememberNavController()
    navController
    NavHost(
        navController = navController,
        startDestination = "Selection"
    )
    {
        composable("Selection"){
            DBSelectionRoot(
                onShowSelected =
                {

                },
                onChangeSelected =
                { table ->
                    navController.navigate("DataChanging/$table")
                }
            )
        }
        composable(
            "DataChanging/{table}",
        )
        {
            DBChangeDataRoot(
                onInteractionSelected =
                { interaction ->
                    navController.navigate("InteractionForm/$interaction")
                }
            )
        }
        composable("InteractionForm/{interaction}"){
            InteractionFormRoot()
        }
        composable("ShowingData"){

        }
    }
}