package com.weg.gestao_escolar.model;

import java.sql.Date;

public class Aluno {
    private Long id;
    private String nome, email, matricula;
    private Date data_nascimento;

    public Aluno(){}

    public Aluno(String nome, String email, String matricula, Date data_nascimento) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
    }

    public Aluno(Long id, String nome, String email, String matricula, Date data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }    
}
