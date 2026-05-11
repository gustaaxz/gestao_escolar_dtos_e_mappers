package com.weg.gestao_escolar.repository.professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Professor;

public class ProfessorRepositoryImpl {
    public static Professor cadastrarProfessor(Professor professor) throws SQLException{
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
}
