package com.focofitness.appexercicios.feature_dieta.use_cases

data class DietaUseCases(
    val getTodosAlimentosUseCase: GetTodosAlimentosUseCase,
    val searchAlimentosUseCase: SearchAlimentosUseCase,
    val getAlimentoSelecionadoUseCase: GetAlimentoSelecionadoUseCase,
    val searchDiaSemanaUseCase: SearchDiaSemanaUseCase,
    val inserirConsumoDiarioUseCase: InserirConsumoDiarioUseCase,
    val deleteConsumoDiarioUseCase: DeleteConsumoDiarioUseCase,
    val inserirAlimentoUseCase: InserirAlimentoUseCase
    )
