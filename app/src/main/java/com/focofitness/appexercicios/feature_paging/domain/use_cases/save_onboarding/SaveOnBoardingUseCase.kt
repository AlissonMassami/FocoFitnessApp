package com.example.borutoapp.domain.use_cases.save_onboarding

import com.focofitness.appexercicios.feature_paging.data.repository.RepositoryPaging


class SaveOnBoardingUseCase(
    private val repository: RepositoryPaging
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}