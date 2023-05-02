package com.focofitness.appexercicios.feature_dieta.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tabela_caloria")
data class TabelaCalorica(
    @PrimaryKey(autoGenerate = false) @SerializedName("Alimentos") @Expose  var alimento: String = "" ,
    @SerializedName("Energia (kcal)") @Expose var caloria : String? = null ,
    @SerializedName("Proteina (g)") @Expose var proteina : String? = null ,
    @SerializedName("Lipidios totais (g)") @Expose var lipidios : String? =null ,
    @SerializedName("Carboidrato (g)") @Expose var carboidrato : String? =null ,
    @SerializedName("Fibra alimentar total (g)") @Expose var fibra : String? = null ,
    var quantidade: String? = null

)
