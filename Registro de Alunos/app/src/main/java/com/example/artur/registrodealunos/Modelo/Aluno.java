package com.example.artur.registrodealunos.Modelo;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Aluno {

    @Id Long id;
    private String nome;
    private String curso;
    private Faculdade faculdade;

    Aluno(){ }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }
}
