package com.weg.gestao_escolar.model;

public class Turma {
    private Long id, curso_id, professor_id;
    private String nome;
    
    public Turma(Long curso_id, Long professor_id, String nome) {
        this.curso_id = curso_id;
        this.professor_id = professor_id;
        this.nome = nome;
    }

    public Turma(Long id, Long curso_id, Long professor_id, String nome) {
        this.id = id;
        this.curso_id = curso_id;
        this.professor_id = professor_id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCurso_id() {
        return curso_id;
    }
    
    public void setCurso_id(Long curso_id) {
        this.curso_id = curso_id;
    }
    
    public Long getProfessor_id() {
        return professor_id;
    }
    
    public void setProfessor_id(Long professor_id) {
        this.professor_id = professor_id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    } 
}
