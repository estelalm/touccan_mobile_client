package br.senai.sp.jandira.touccanclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.touccanclient.model.ClientId
import br.senai.sp.jandira.touccanclient.screens.CreateAnuncio
import br.senai.sp.jandira.touccanclient.screens.Home
import br.senai.sp.jandira.touccanclient.screens.Login
import br.senai.sp.jandira.touccanclient.ui.theme.TouccanClientTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TouccanClientTheme {

                Surface(color = Color(0xffEBEBEB)) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "logIn") {


                        composable(route = "logIn"){ Login(navController) }

                        composable(route = "home/{id}",
                            arguments = listOf(navArgument("id") {
                            })
                        ){ backStackEntry ->
                            val clientId = backStackEntry.arguments?.getString("id")
                            Log.i("Client: ", clientId.toString())
                            val idCliente = Json.decodeFromString<ClientId>(clientId ?: "")
                            Home(navController, idCliente, this@MainActivity) }

                        composable(route = "criarAnuncio/{id}",
                            arguments = listOf(navArgument("id") {
                            })
                        ){ backStackEntry ->
                            val clientId = backStackEntry.arguments?.getString("id")
                            Log.i("User: ", clientId.toString())
                            val idCliente = Json.decodeFromString<ClientId>(clientId ?: "")
                            CreateAnuncio(navController, idCliente, this@MainActivity) }
                    }

                }

                }
            }
        }
    }

