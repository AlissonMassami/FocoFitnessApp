package com.focofitness.appexercicios.feature_paging.presentation.paging_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.focofitness.appexercicios.R
import com.focofitness.appexercicios.domain.model.Usuario
import com.focofitness.appexercicios.domain.util.getBitmapFromImage
import com.focofitness.appexercicios.feature_dieta.component.cadastroAlimentos
import com.focofitness.appexercicios.feature_paging.domain.model.OnBoardingPage
import com.focofitness.appexercicios.navegation.Screen
import com.focofitness.appexercicios.ui.theme.LightBlue
import com.focofitness.appexercicios.ui.theme.activeIndicatorColor
import com.focofitness.appexercicios.ui.theme.buttonBackgroundColor
import com.focofitness.appexercicios.ui.theme.inactiveIndicatorColor
import com.focofitness.appexercicios.util.Constants.LAST_ON_BOARDING_PAGE
import com.focofitness.appexercicios.util.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.*


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController ,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val icon = getBitmapFromImage(context, R.mipmap.ic_profile_foreground)
    val user = Usuario(
            id = 1,
            nome = "Sem Cadastro" ,
            peso = 60f ,
            idade = 20 ,
            foto = icon)
   val cadastroAlimentos = cadastroAlimentos(context)

    welcomeViewModel.cadastroAliementos(cadastroAlimentos)
    welcomeViewModel.atualizarPerfil(user)

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth,
        OnBoardingPage.Fifth,
        )

    val pagerState = rememberPagerState()

    Surface(
        Modifier
            .background(LightBlue)
            .fillMaxSize(),
        color = LightBlue
    ) {
        Box {
            HorizontalPager(

                state = pagerState ,
                verticalAlignment = Alignment.Top ,
                count = ON_BOARDING_PAGE_COUNT
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])
            }
        }
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.activeIndicatorColor,
                inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
                indicatorWidth = 12.dp,
                spacing = 8.dp
            )
            FinishButton(
                modifier = Modifier,
                pagerState = pagerState
            ) {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
                welcomeViewModel.saveOnBoardingState(completed = true)
            }
        }
    }
}




@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = onBoardingPage.image) ,
            contentDescription = "On Boarding Image"
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier ,
    pagerState: PagerState ,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        if(pagerState.currentPage == LAST_ON_BOARDING_PAGE){
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE
            ) {
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Finalizar")
                }
            }
        }else{
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = pagerState.currentPage != LAST_ON_BOARDING_PAGE
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Row (horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = "Deslize para continuar" ,
                            color = Color.White
                        )
                        Icon(imageVector = Icons.Filled.ArrowForward , contentDescription = "Seta Direita")
                    }
                }
            }
        }


    }
}