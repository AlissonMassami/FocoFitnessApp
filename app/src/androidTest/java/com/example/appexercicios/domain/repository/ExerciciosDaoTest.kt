package com.example.appexercicios.domain.repository

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.graphics.painter.Painter
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.appexercicios.component.PickImageFromGallery
import com.example.appexercicios.data.local.AppDatabase
import com.example.appexercicios.data.local.dao.ExerciciosDAO
import com.example.appexercicios.domain.model.Exercicios
import com.example.appexercicios.domain.model.ListaExercicios
import com.example.appexercicios.domain.model.RotinaDiaria
import com.example.appexercicios.domain.model.Usuario
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ExerciciosDaoTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ExerciciosDAO


    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.exerciciosDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun inserirExercicioBuscarExercicio() = runBlockingTest {
        val exercicioItem = Exercicios(
            "Teste",
            "testeImage",
            "Tipo Exerc",
            3,
            12)
        dao.addExercicio(exercicioItem)
        dao.buscarTodosExercicios().test{
            val list = awaitItem()
            assert(list.contains(exercicioItem))
            cancel()
        }
    }

    @Test
    fun deletarExercicio() = runBlockingTest {
        val exercicioItem = Exercicios(
            "Teste",
            "testeImage",
            "Tipo Exerc",
            3,
            12)
        dao.addExercicio(exercicioItem)
        dao.buscarTodosExercicios().test{
            val list = awaitItem()
            assert(list.contains(exercicioItem))
        }
        dao.deletarExercicio(exercicioItem)
        dao.buscarTodosExercicios().test{
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }
    @Test
    fun inserirBuscarRotina() = runBlockingTest {
        val rotinaDiaria = RotinaDiaria(
            "SEG",
        ListaExercicios(
            emptyList(),
        ),
            rotinaCompleta = false
        )
        dao.addRotina(rotinaDiaria)
        dao.buscarRotinas().test{
            val list = awaitItem()
            assert(list.contains(rotinaDiaria))
            cancel()
        }
    }

    @Test
    fun deletarRotina() = runBlockingTest {
        val rotinaDiaria = RotinaDiaria(
            "SEG",
            ListaExercicios(
                emptyList(),
            ),
            rotinaCompleta = false
        )
        dao.addRotina(rotinaDiaria)
        dao.buscarRotinas().test{
            val list = awaitItem()
            assert(list.contains(rotinaDiaria))
        }
        dao.deletarRotina(rotinaDiaria)
        dao.buscarRotinas().test{
            val list = awaitItem()
            assert(list.isNullOrEmpty())
            cancel()
        }
    }

//    @Test
//    fun inserirBuscarUsuario() = runBlockingTest {
//
//        val user = Usuario(
//            id = 1,
//            nome = "Teste",
//            peso = 50.5f,
//            idade = 30,
//            foto =
//        )
//        dao.addUsuario(usuario = user)
//        val busca = dao.buscarUsuario(1)
//        assertThat(busca).isEqualTo(user)
//    }
}