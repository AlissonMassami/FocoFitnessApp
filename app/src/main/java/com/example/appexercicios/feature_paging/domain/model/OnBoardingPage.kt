package com.example.appexercicios.feature_paging.domain.model

import androidx.annotation.DrawableRes
import com.example.appexercicios.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
) {
    object First : OnBoardingPage(
        image = R.drawable.bemvinda_tela1,
       )

    object Second : OnBoardingPage(
        image = R.drawable.bemvinda_tela2,
    )

    object Third : OnBoardingPage(
        image = R.drawable.bemvinda_tela3 ,
    )
    object Fourth : OnBoardingPage(
        image = R.drawable.bemvinda_tela4,
    )
    object Fifth : OnBoardingPage(
        image = R.drawable.bemvinda_tela5,
    )
}

