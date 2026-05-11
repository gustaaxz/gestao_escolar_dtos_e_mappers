package com.weg.gestao_escolar.model;

public class Turma_Aluno {
    private Long turma_id, aluno_id;

    public Turma_Aluno(Long turma_id, Long aluno_id) {
        this.turma_id = turma_id;
        this.aluno_id = aluno_id;
    }

    public Long getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Long turma_id) {
        this.turma_id = turma_id;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }
}
