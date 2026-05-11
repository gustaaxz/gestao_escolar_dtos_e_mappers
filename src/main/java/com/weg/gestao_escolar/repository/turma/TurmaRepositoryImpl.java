package com.weg.gestao_escolar.repository.turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Turma;

public class TurmaRepositoryImpl {
    public static Turma cadastrarTurma(Turma turma) throws SQLException {
        String command = """
                INSERT INTO turma
                (nome, curso_id, professor_id)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, 
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, turma.getNome());
                stmt.setLong(2, turma.getCurso_id());
                stmt.setLong(3, turma.getProfessor_id());
            
                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    turma.setId(id);
                    return turma;
                }
        }
        throw new RuntimeException("Erro ao cadastrar uma turma nova!");
    }
}
