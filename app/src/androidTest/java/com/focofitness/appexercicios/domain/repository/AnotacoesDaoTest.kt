package com.focofitness.appexercicios.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.focofitness.appexercicios.data.local.AppDatabase
import com.focofitness.appexercicios.feature_medidas.data.data_source.MedidasDAO
import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AnotacoesDaoTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: MedidasDAO


    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.medidasDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun inserirNovaNotaBuscarExercicio() = runBlockingTest {
        val notaItem = Medidas(
            title = "titulo" ,
            content = "conteudo" ,
            timestamp = "maio" ,
            id = 4
        )

        dao.insertNotaMedida(notaItem)
        dao.getNotasMedidas().test{
            val list = awaitItem()
            assert(list.contains(notaItem))
            cancel()
        }
    }

    @Test
    fun deletarNota() = runBlockingTest {
        val notaItem = Medidas(
            title = "titulo" ,
            content = "conteudo" ,
            timestamp = "maio" ,
            id = 4
        )
        dao.insertNotaMedida(notaItem)
        dao.getNotasMedidas().test{
            val list = awaitItem()
            assert(list.contains(notaItem))

        }
        dao.deleteNotaMedida(notaItem)
        dao.getNotasMedidas().test{
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }
   
}