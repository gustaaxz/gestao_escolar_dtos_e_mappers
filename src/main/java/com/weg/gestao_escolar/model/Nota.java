package com.weg.gestao_escolar.model;

public class Nota {
    private Long id, aluno_id, aula_id;
    private Double valor;

    public Nota(){}

    public Nota(Long aluno_id, Long aula_id, Double valor) {
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public Nota(Long id, Long aluno_id, Long aula_id, Double valor) {
        this.id = id;
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Long getAula_id() {
        return aula_id;
    }

    public void setAula_id(Long aula_id) {
        this.aula_id = aula_id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
