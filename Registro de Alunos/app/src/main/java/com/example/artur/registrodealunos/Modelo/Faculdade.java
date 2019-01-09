package com.example.artur.registrodealunos.Modelo;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Faculdade {

    @Id Long id;
    private String nome;
    private String email;
    private String contatoPrincipal;
    private List<Aluno> alunos;

    Faculdade() { }

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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public String getContatoPrincipal() {
        return contatoPrincipal;
    }

    public void setContatoPrincipal(String contatoPrincipal) {
        this.contatoPrincipal = contatoPrincipal;
    }
}
