package com.focofitness.appexercicios.feature_dieta.use_cases

import com.focofitness.appexercicios.data.repository.FakeDietaRepository
import com.focofitness.appexercicios.feature_dieta.domain.model.TabelaCalorica
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetAlimentoSelecionadoUseCaseTest{

    private lateinit var getAlimentoSelecionadoUseCase: GetAlimentoSelecionadoUseCase
    private lateinit var fakeDietaRepository: FakeDietaRepository

    @Before
    fun setUp() {
        fakeDietaRepository = FakeDietaRepository()
        getAlimentoSelecionadoUseCase = GetAlimentoSelecionadoUseCase(fakeDietaRepository)

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

    @Test
    fun buscaAlimentoSelecionadoRetornaTrue()= runBlockingTest {
        val item = TabelaCalorica(
            alimento = "a",
        )
        val alimento = fakeDietaRepository.getAlimento(item.alimento)
        assert(alimento?.alimento.equals("a"))
    }

    @Test
    fun buscaAlimentoSelecionadoRetornaFalse()= runBlockingTest {
        val item = TabelaCalorica(
            alimento = "a",
        )
        val alimento = fakeDietaRepository.getAlimento(item.alimento)
        assert(!alimento?.alimento.equals("adsasda"))
    }
}