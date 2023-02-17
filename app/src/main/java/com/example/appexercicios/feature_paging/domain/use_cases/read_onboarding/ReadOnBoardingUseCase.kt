package com.example.borutoapp.domain.use_cases.read_onboarding


import com.example.appexercicios.feature_paging.data.repository.RepositoryPaging
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: RepositoryPaging
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}