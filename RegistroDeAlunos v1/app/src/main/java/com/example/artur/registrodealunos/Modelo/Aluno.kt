package com.example.artur.registrodealunos.Modelo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Aluno (var nome: String,
                  var curso: String){
    @Id var id: Long = 0
}