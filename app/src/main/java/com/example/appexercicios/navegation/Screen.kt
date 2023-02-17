package com.example.appexercicios.navegation

sealed class Screen(val route: String) {
    //    object Login : Screen(route = "login_screen")
    object Home: Screen(route = "home_screen")
    object PagExercicios: Screen(route = "pg_exercicios_screen")
    object CriarRotina: Screen(route = "criar_rotina_screen/{dia}")
    object Perfil: Screen(route = "perfil_screen")
    object MedidasTela: Screen(route = "medidas_screen")
    object MedidasEditar: Screen(route = "medidas_editar_screen")
    object Welcome: Screen(route = "welcome_screen")
    object Splash: Screen(route = "splash_screen")


}

