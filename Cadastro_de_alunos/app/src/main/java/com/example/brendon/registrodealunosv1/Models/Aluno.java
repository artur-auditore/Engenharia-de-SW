package com.example.brendon.registrodealunosv1.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Aluno {

    @Id
    public long id;
    private String nome;
    private String curso;
    private String faculdade;

    Aluno() { }

    public Aluno(String nome, String curso, String faculdade) {
        this.nome = nome;
        this.curso = curso;
        this.faculdade = faculdade;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }
}
