package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.data.repository.FakeDietaRepository
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchDiaSemanaUseCaseTest{
    private lateinit var searchDiaSemanaUseCase: SearchDiaSemanaUseCase
    private lateinit var fakeDietaRepository: FakeDietaRepository

    @Before
    fun setUp() {
        fakeDietaRepository = FakeDietaRepository()
        searchDiaSemanaUseCase = SearchDiaSemanaUseCase(fakeDietaRepository)

        val listaConsumo = mutableListOf<CaloriasDiarias>()
        ('a'..'z').forEachIndexed { index, c ->
            listaConsumo.add(
                CaloriasDiarias(
                    diaSemana = c.toString(),
                    listaAlimentos = emptyList()
                )
            )
        }

        runBlocking {
            listaConsumo.forEach { fakeDietaRepository.insertConsumoDiario(it) }
        }
    }


    @Test
    fun buscaDiaDaSemanaRetornaTrue()= runBlockingTest{
        val item = CaloriasDiarias(
            diaSemana = "a",
            listaAlimentos = emptyList()
        )
        val busca = fakeDietaRepository.searchDiaSemana("a")
        assertEquals(busca,item)
    }

}