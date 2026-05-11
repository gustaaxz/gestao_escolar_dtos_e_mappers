package com.weg.gestao_escolar.repository.professor;

import java.sql.SQLException;
import java.util.List;

import com.weg.gestao_escolar.controller.Professor;

public interface ProfessorRepository {
    Professor cadastrarProfessor(Professor professor) throws SQLException;
    Professor buscarProfessor(Long id) throws SQLException;
    List<Professor> buscarTodosProfessores() throws SQLException;
    void atualizarProfessor(Professor professor) throws SQLException;
    void deletarProfessor(Long id) throws SQLException;
}
