@file:OptIn(ExperimentalPermissionsApi::class)

package com.focofitness.appexercicios.navegation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.focofitness.appexercicios.feature_medidas.presentation.add_edit_medida.AddEditMedidaScreen
import com.focofitness.appexercicios.feature_paging.presentation.paging_screen.WelcomeScreen
import com.focofitness.appexercicios.presentation.CriarRotina.CriarRotinaScreen
import com.focofitness.appexercicios.presentation.PagExercicios.ExerciciosScreen
import com.focofitness.appexercicios.presentation.PagInicial.HomeScreen
import com.focofitness.appexercicios.presentation.Perfil.PerfilScreen
import com.focofitness.appexercicios.presentation.Splash.SplashScreen
import com.focofitness.appexercicios.presentation.Treino.TreinoScreen
import com.example.appexercicios.presentation.medidas.MedidasScreen
import com.focofitness.appexercicios.feature_dieta.presentation.DietaScreen.DietaScreen
import com.focofitness.appexercicios.feature_dieta.presentation.cadastroAlimento.CadastroAlimentoScreen

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Home.route)
        {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.PagExercicios.route) {
            ExerciciosScreen(
                navController = navController
            )
        }
        composable(route = "criar_rotina_screen/{dia}",
                arguments = listOf(navArgument("dia"){type = NavType.StringType}
                )
        ) {
            CriarRotinaScreen(navController = navController, dia = it.arguments?.getString("dia"))
        }
        composable(route = "iniciar_treino/{dia}",
            arguments = listOf(
                navArgument("dia"){
                    type = NavType.StringType
                }
            )
        ) {
            TreinoScreen(navController = navController, dia = it.arguments?.getString("dia"))
        }
        composable(route = Screen.Perfil.route)
        {
            PerfilScreen(navController = navController)
        }
        composable(route = Screen.MedidasTela.route) {
           MedidasScreen(navController = navController)
        }
        composable(
            route = Screen.MedidasEditar.route +
                    "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditMedidaScreen(
                navController = navController,

            )
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Dieta.route) {
            DietaScreen(navController = navController)
        }
        composable(route = Screen.CadastroAlimento.route) {
            CadastroAlimentoScreen(navController = navController)
        }

    }
}