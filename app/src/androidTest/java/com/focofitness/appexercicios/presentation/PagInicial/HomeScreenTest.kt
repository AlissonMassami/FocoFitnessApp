@file:OptIn(ExperimentalMaterialApi::class , ExperimentalPagerApi::class)

package com.focofitness.appexercicios.presentation.PagInicial

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.focofitness.appexercicios.MainActivity
import com.focofitness.appexercicios.di.DatabaseModule
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.navegation.SetupNavGraph
import com.focofitness.appexercicios.ui.theme.AppExerciciosTheme
import com.focofitness.appexercicios.util.TestTags
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test



@OptIn(ExperimentalAnimationApi::class)
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class HomeScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @OptIn(ExperimentalPagerApi::class)
    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalPagerApi::class)
    @Before
    fun setUp(){
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            AppExerciciosTheme {
                SetupNavGraph(navController = navController)
            }

        }
    }

    @Test
    fun clickEditarPerfil_mudaJanela(){
        composeRule.onNodeWithContentDescription("botao_direito").performClick()
    }
    @Test
    fun clickIniciarTreino_mudaJanela(){
        composeRule.onNodeWithContentDescription("icone_treino").performClick()
    }
    @Test
    fun clickExercicios_mudaJanela(){
        composeRule.onNodeWithContentDescription("icone_exercicio").performClick()
    }
    @Test
    fun clickMedidas_mudaJanela(){
        composeRule.onNodeWithContentDescription("icone_medidas").performClick()
    }
    @Test
    fun clickDieta_mudaJanela(){
        composeRule.onNodeWithContentDescription("icone_dieta").performClick()
    }
    @Test
    fun mostrarImagemPerfilPadrao(){
        composeRule.onNodeWithContentDescription("imagem_perfil_padrao").assertIsDisplayed()
    }

    @Test
    fun clicarBotaoDom_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_DOM).performClick()
    }

    @Test
    fun clicarBotaoSeg_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_SEG).performClick()
    }

    @Test
    fun clicarBotaoTer_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_TER).performClick()
    }

    @Test
    fun clicarBotaoQua_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_QUA).performClick()
    }

    @Test
    fun clicarBotaoQui_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_QUI).performClick()
    }

    @Test
    fun clicarBotaoSex_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_SEX).performClick()
    }

    @Test
    fun clicarBotaoSab_mudaJanela(){
        composeRule.onNodeWithTag(TestTags.ROTINA_SAB).performClick()
    }
}