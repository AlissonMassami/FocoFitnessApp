package com.example.appexercicios.feature_medidas.domain.use_case

import com.example.appexercicios.data.repository.FakeMedidasRepository
import com.example.appexercicios.feature_medidas.domain.model.InvalidNoteException
import com.example.appexercicios.feature_medidas.domain.model.Medidas
import com.google.common.truth.Truth.assertThat


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert


import org.junit.Before
import org.junit.Test

class GetTodasNotasTest{

    private lateinit var getTodasNotas: GetTodasNotas
    private lateinit var fakeMedidasRepository: FakeMedidasRepository
    private lateinit var inserirNota: InserirNota


    @Before
    fun setUp(){
        fakeMedidasRepository = FakeMedidasRepository()
        getTodasNotas = GetTodasNotas(fakeMedidasRepository)

        val anotacoesParaInserir = mutableListOf<Medidas>()
        ('a'..'z').forEachIndexed { index, c ->
            anotacoesParaInserir.add(
                Medidas(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = "12121"
                )
            )
        }

        runBlocking {
            anotacoesParaInserir.forEach { fakeMedidasRepository.insertNotaMedida(it) }
        }
    }

    @Test
    fun AdicionarMedidaTituloEmBranco_ThrowsExeception()= runBlockingTest{
        val medida = Medidas(
            title = "",
            content = "sssss",
            timestamp = "12121"
        )
        try {

        }catch (e : InvalidNoteException){
            assertThat(e).hasMessageThat()
        }
    }
    @Test
    fun AdicionarMedidaConteudoEmBranco_ThrowsExeception()= runBlockingTest{
        val medida = Medidas(
            title = "ssssss",
            content = "",
            timestamp = "12121"
        )
        try {

        }catch (e : InvalidNoteException){
            assertThat(e).hasMessageThat()
        }
    }

    @Test
    fun GetTodasNotasRetornaListFlow()= runBlockingTest{
        assertThat(getTodasNotas.invoke().first().first().title).isEqualTo("a")
        assertThat(getTodasNotas.invoke().first().last().title).isEqualTo("z")
    }
}