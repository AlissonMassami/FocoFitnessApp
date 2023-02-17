package com.example.appexercicios.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.appexercicios.MainActivity
import com.example.appexercicios.di.DatabaseModule
import com.example.appexercicios.navegation.SetupNavGraph
import com.example.appexercicios.ui.theme.AppExerciciosTheme
import com.example.appexercicios.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class EndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @OptIn(ExperimentalCoilApi::class)
    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

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
    fun irParaJanelaDeExercicios_adicionarNovoExercicio(){
        composeRule.onNodeWithContentDescription("icone_exercicio").performClick()

        composeRule.onNodeWithContentDescription("botao_direito").performClick()
        composeRule
            .onNodeWithTag(TestTags.NOME_EXERCICIO_TEXT_FIELD)
            .performTextInput("Test-Exercicio")
        composeRule
            .onNodeWithTag(TestTags.TIPO_EXERCICIO_TEXT_FIELD)
            .performTextInput("Test-Tipo")
        composeRule
            .onNodeWithTag(TestTags.SETS_EXERCICIO_TEXT_FIELD)
            .performTextInput("1")
        composeRule
            .onNodeWithTag(TestTags.REPS_EXERCICIO_TEXT_FIELD)
            .performTextInput("1")
        composeRule.onNodeWithTag(TestTags.BOTAO_ADICIONAR_EXERCICIOS).performClick()
        composeRule.onNodeWithText("TEST-EXERCICIO").assertIsDisplayed()
    }

    @Test
    fun clicarDiaSemana_SalvarNovaRotina(){
        //clicar botao domingo
        composeRule.onNodeWithTag(TestTags.ROTINA_DOM).performClick()

        //criar dois exercicios testes
        composeRule.onNodeWithContentDescription("botao_direito").performClick()
        composeRule
            .onNodeWithTag(TestTags.NOME_EXERCICIO_TEXT_FIELD)
            .performTextInput("Test-Exercicio")
        composeRule
            .onNodeWithTag(TestTags.TIPO_EXERCICIO_TEXT_FIELD)
            .performTextInput("Test-Tipo")
        composeRule
            .onNodeWithTag(TestTags.SETS_EXERCICIO_TEXT_FIELD)
            .performTextInput("1")
        composeRule
            .onNodeWithTag(TestTags.REPS_EXERCICIO_TEXT_FIELD)
            .performTextInput("1")
        composeRule.onNodeWithTag(TestTags.BOTAO_ADICIONAR_EXERCICIOS).performClick()
        composeRule.onNodeWithContentDescription("botao_direito").performClick()
        composeRule
            .onNodeWithTag(TestTags.NOME_EXERCICIO_TEXT_FIELD)
            .performTextInput("2")
        composeRule
            .onNodeWithTag(TestTags.TIPO_EXERCICIO_TEXT_FIELD)
            .performTextInput("2")
        composeRule
            .onNodeWithTag(TestTags.SETS_EXERCICIO_TEXT_FIELD)
            .performTextInput("1")
        composeRule
            .onNodeWithTag(TestTags.REPS_EXERCICIO_TEXT_FIELD)
            .performTextInput("1")
        composeRule.onNodeWithTag(TestTags.BOTAO_ADICIONAR_EXERCICIOS).performClick()
        //verificar se apareceu na coluna de exercicios
        composeRule.onNodeWithContentDescription("Test-Exercicio").performClick()
        //salvar rotina
        composeRule.onNodeWithTag(TestTags.BOTAO_SALVAR_ROTINA).performClick()
        composeRule.onNodeWithContentDescription("botao_voltar").performClick()

        //voltou para home screen
        //clicar botao domingo
        composeRule.onNodeWithTag(TestTags.ROTINA_DOM).performClick()
        composeRule.onNodeWithTag(TestTags.COLUNA_ROTINA)
            .onChildren()
            .assertCountEquals(1)
    }

}