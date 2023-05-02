package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.data.repository.FakeDietaRepository
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

class GetTodosAlimentosUseCaseTest{
    private lateinit var getTodosAlimentosUseCase: GetTodosAlimentosUseCase
    private lateinit var fakeDietaRepository: FakeDietaRepository

    @Before
    fun setUp() {
        fakeDietaRepository = FakeDietaRepository()
        getTodosAlimentosUseCase = GetTodosAlimentosUseCase(fakeDietaRepository)

        val listaConsumo = mutableListOf<TabelaCalorica>()
        ('a'..'z').forEachIndexed { index, c ->
            listaConsumo.add(
                TabelaCalorica(
                    alimento = c.toString()
                )
            )
        }

        runBlocking {
            listaConsumo.forEach { fakeDietaRepository.insertAlimento(it) }
        }
    }



    @After
    fun tearDown() {

    }


}