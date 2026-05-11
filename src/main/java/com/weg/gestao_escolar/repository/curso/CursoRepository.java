package com.weg.gestao_escolar.repository.curso;

import java.sql.SQLException;
import java.util.List;

import com.weg.gestao_escolar.model.Curso;

public interface CursoRepository {
    Curso cadastrarCurso(Curso curso) throws SQLException;
    Curso buscarCurso(Long id) throws SQLException;
    List<Curso> buscarTodosCursos() throws SQLException;
    void atualizarCurso(Curso curso) throws SQLException;
    void deletarCurso(Long id) throws SQLException;
}
