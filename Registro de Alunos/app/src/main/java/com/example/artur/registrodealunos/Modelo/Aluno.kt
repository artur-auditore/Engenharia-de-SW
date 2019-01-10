package com.example.artur.registrodealunos.Modelo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Aluno (var nome: String,
                  var curso: String){
    @Id var id: Long = 0
    lateinit var faculdade: ToOne<Faculdade>
}