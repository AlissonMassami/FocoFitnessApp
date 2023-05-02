package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.data.repository.FakeDietaRepository
import com.focofitness.appexercicios.feature_dieta.domain.model.CaloriasDiarias
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class DeleteConsumoDiarioUseCaseTest {

    private lateinit var deleteConsumoDiarioUseCase: DeleteConsumoDiarioUseCase
    private lateinit var fakeDietaRepository: FakeDietaRepository

    @Before
    fun setUp() {
        fakeDietaRepository = FakeDietaRepository()
        deleteConsumoDiarioUseCase = DeleteConsumoDiarioUseCase(fakeDietaRepository)

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



    @After
    fun tearDown() {

    }

    @Test
    fun deleleConsumoDiarioRetornaTrue()= runBlockingTest {
        val item = CaloriasDiarias(
            diaSemana = "a",
            listaAlimentos = emptyList()
        )
        fakeDietaRepository.deleteConsumoDiario(item)
        val lista = fakeDietaRepository.searchDiaSemana(item.diaSemana)

        assertNull(lista)
    }
}