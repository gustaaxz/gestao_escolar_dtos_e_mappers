package com.weg.gestao_escolar.repository.professor;

import java.sql.SQLException;
import java.util.List;
import com.weg.gestao_escolar.model.Professor;

public interface ProfessorRepository {
    Professor cadastrarProfessor(Professor professor) throws SQLException;
    Professor buscarProfessor(Long id) throws SQLException;
    List<Professor> buscarTodosProfessores() throws SQLException;
    Professor atualizarProfessor(Professor Professor) throws SQLException;
    void deletarProfessor(Long id) throws SQLException;
    boolean existePorId(Long id) throws SQLException;
}
