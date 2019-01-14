package com.example.artur.registrodealunos.Modelo;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

import java.util.List;

@Entity
public class Faculdade {

    @Id private long id;
    private String nome;
    private String email;
    private String contatoPrincipal;
    private List<Aluno> alunos;

    Faculdade() {}

    public Faculdade(String nome, String email, String contatoPrincipal) {
        this.nome = nome;
        this.email = email;
        this.contatoPrincipal = contatoPrincipal;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContatoPrincipal() {
        return contatoPrincipal;
    }

    public void setContatoPrincipal(String contatoPrincipal) {
        this.contatoPrincipal = contatoPrincipal;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
