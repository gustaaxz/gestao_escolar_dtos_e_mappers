package com.weg.gestao_escolar.repository.curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Curso;

@Repository
public class CursoRepositoryImpl {
    public static Curso cadastrarCurso(Curso curso) throws SQLException {
        String command = """
                INSERT INTO curso
                (nome, codigo)
                VALUES
                (?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, 
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, curso.getNome());
                stmt.setString(2, curso.getCodigo());

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    curso.setId(id);
                    return curso;
                }
            }
        throw new RuntimeException("Erro ao criar um novo curso!");
    }
}
