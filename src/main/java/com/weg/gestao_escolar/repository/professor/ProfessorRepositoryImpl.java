package com.weg.gestao_escolar.repository.professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Professor;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {
    public Professor cadastrarProfessor(Professor professor) throws SQLException{
        String command = """
                INSERT INTO professor
                (nome, email, disciplina)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command,
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, professor.getNome());
                stmt.setString(2, professor.getEmail());
                stmt.setString(3, professor.getDisciplina());

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    professor.setId(id);
                    return professor;
                }
            }
        throw new RuntimeException("Erro ao criar um novo Professor!");
    }

    @Override
    public com.weg.gestao_escolar.controller.Professor cadastrarProfessor(
            com.weg.gestao_escolar.controller.Professor professor) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarProfessor'");
    }

    @Override
    public com.weg.gestao_escolar.controller.Professor buscarProfessor(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProfessor'");
    }

    @Override
    public List<com.weg.gestao_escolar.controller.Professor> buscarTodosProfessores() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodosProfessores'");
    }

    @Override
    public void atualizarProfessor(com.weg.gestao_escolar.controller.Professor professor) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarProfessor'");
    }

    @Override
    public void deletarProfessor(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarProfessor'");
    }
    
    @Override
    public boolean existePorId(Long id) throws SQLException{
        String query = """
                    SELECT COUNT(0) AS resultado
                    FROM professor
                    WHERE id = ?
                    """;
                
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int resultado = rs.getInt("resultado");
                if(resultado == 1){
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new RuntimeException("Erro ao buscar se o professor existe!");
    }
}
