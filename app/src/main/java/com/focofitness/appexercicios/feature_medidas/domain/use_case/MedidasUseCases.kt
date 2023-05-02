package com.focofitness.appexercicios.feature_medidas.domain.use_case

data class MedidasUseCases (
    val getNota: GetNota,
    val getTodasNotas: GetTodasNotas,
    val deleteNota: DeleteNota,
    val inserirNota: InserirNota
        )