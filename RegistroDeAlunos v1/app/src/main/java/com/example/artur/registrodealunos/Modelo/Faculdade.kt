package com.example.artur.registrodealunos.Modelo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Faculdade (var nome: String,
                      var email: String,
                      var contatoPrincipal: String) {
    @Id var id: Long = 0
    var alunos = listOf<Aluno>()

}