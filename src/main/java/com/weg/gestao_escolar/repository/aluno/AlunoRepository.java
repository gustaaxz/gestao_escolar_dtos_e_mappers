package com.weg.gestao_escolar.repository.aluno;

import java.sql.SQLException;
import java.util.List;

import com.weg.gestao_escolar.model.Aluno;

public interface AlunoRepository {
    Aluno cadastrarAluno(Aluno aluno) throws SQLException;
    Aluno buscarAluno(Long id) throws SQLException;
    List<Aluno> buscarTodosAlunos() throws SQLException;
    void atualizarAluno(Aluno aluno) throws SQLException;
    void deletarAluno(Long id) throws SQLException;
}
