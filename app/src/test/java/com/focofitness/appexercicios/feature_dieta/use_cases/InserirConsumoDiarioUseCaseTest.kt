package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.data.repository.FakeDietaRepository
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class InserirConsumoDiarioUseCaseTest{
    private lateinit var inserirConsumoDiarioUseCase: InserirConsumoDiarioUseCase
    private lateinit var fakeDietaRepository: FakeDietaRepository

    @Before
    fun setUp() {
        fakeDietaRepository = FakeDietaRepository()
        inserirConsumoDiarioUseCase = InserirConsumoDiarioUseCase(fakeDietaRepository)

        val listaConsumo = mutableListOf<CaloriasDiarias>()
        ('a'..'z').forEachIndexed { index, c ->
            listaConsumo.add(
                CaloriasDiarias(
                    diaSemana = "25-03-2022",
                    listaAlimentos = emptyList()
                )
            )
        }

        runBlocking {
            listaConsumo.forEach { fakeDietaRepository.insertConsumoDiario(it) }
        }
    }



    @After
    fun tearDown() {

    }

    @Test
    fun insererCaloriaDiariaRetornaTrue()= runBlockingTest{
        val item = CaloriasDiarias(
            diaSemana = "novo dia",
            listaAlimentos = emptyList()
        )
        fakeDietaRepository.insertConsumoDiario(item)
        val lista = fakeDietaRepository.searchDiaSemana(item.diaSemana)
        assertEquals(lista,item)
    }
}