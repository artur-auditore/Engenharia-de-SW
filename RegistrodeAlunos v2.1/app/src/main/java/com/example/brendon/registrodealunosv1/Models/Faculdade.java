package com.example.brendon.registrodealunosv1.Models;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Faculdade {

    @Id
    public long id;
    private String nome;
    private String email;
    private String contatoPrincipal;
    //private List<String> alunos;

    Faculdade() { }

    public Faculdade(String nome, String email, String contatoPrincipal) {
        this.nome = nome;
        this.email = email;
        this.contatoPrincipal = contatoPrincipal;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getContatoPrincipal() {
        return contatoPrincipal;
    }
}
