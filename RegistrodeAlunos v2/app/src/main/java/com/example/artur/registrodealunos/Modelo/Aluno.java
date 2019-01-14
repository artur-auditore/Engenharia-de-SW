package com.example.artur.registrodealunos.Modelo;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Aluno {

    @Id
    private long id;
    private String nome;
    private String curso;
    private ToOne<Faculdade> faculdade;

    Aluno(){}

    public Aluno(String nome, String curso) {
        this.nome = nome;
        this.curso = curso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public ToOne<Faculdade> getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(ToOne<Faculdade> faculdade) {
        this.faculdade = faculdade;
    }
}
