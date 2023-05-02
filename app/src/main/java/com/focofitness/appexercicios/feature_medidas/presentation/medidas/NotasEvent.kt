package com.focofitness.appexercicios.feature_medidas.presentation.medidas

import com.focofitness.appexercicios.feature_medidas.domain.model.Medidas

sealed class NotasEvent
{
    data class DeleteNote(val medidas: Medidas): NotasEvent()
    object RestoreNote: NotasEvent()

}