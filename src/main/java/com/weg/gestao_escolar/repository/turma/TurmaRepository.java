package com.weg.gestao_escolar.repository.turma;

import java.sql.SQLException;
import java.util.List;

import com.weg.gestao_escolar.model.Turma;

public interface TurmaRepository {
    Turma cadastrarTurma(Turma turma) throws SQLException;
    Turma buscarTurma(Long id) throws SQLException;
    List<Turma> buscarTodasTurmas() throws SQLException;
    void atualizarTurma(Turma turma) throws SQLException;
    void deletarTurma(Long id) throws SQLException;
    boolean existePorId(Long id) throws SQLException;
}
