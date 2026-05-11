package com.weg.gestao_escolar.repository.aula;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Aula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AulaRepositoryImpl {
    public static Aula cadastrarAula(Aula aula) throws SQLException {
        String command = """
                INSERT INTO aula
                (turma_id, data_hora, assunto)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, 
            PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setLong(1, aula.getTurma_id());
                stmt.setObject(2, aula.getData_hora());
                stmt.setString(3, aula.getAssunto());

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    aula.setId(id);
                    return aula;
                }
            }
        throw new RuntimeException("Erro ao criar uma nova aula!");
    }
}
