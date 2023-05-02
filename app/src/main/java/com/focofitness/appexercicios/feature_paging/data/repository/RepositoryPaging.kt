package com.focofitness.appexercicios.feature_paging.data.repository

import com.focofitness.appexercicios.feature_paging.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryPaging @Inject constructor(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
    dataStore.saveOnBoardingState(completed = completed)
}

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}