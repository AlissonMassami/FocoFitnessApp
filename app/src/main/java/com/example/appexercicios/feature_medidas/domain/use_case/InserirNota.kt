package com.example.appexercicios.feature_medidas.domain.use_case

import com.example.appexercicios.feature_medidas.domain.model.InvalidNoteException
import com.example.appexercicios.feature_medidas.domain.model.Medidas
import com.example.appexercicios.feature_medidas.domain.repository.MedidasRepository

class InserirNota(
    private val repository: MedidasRepository
) {

        @Throws(InvalidNoteException::class)
        suspend operator fun invoke(medidas: Medidas) {
            if(medidas.title.isBlank()) {
                throw InvalidNoteException("Título não pode estar vazio.")
            }
            if(medidas.content.isBlank()) {
                throw InvalidNoteException("Conteúdo não pode estar vazio.")
            }
            repository.insertNotaMedida(medidas)
        }

}