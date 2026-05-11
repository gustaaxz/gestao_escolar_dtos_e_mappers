package com.weg.gestao_escolar.repository.nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Nota;

public class NotaRepositoryImpl {
    public static Nota cadastrarNota(Nota nota) throws SQLException {
        String command = """
                INSERT INTO nota
                (aluno_id, aula_id, valor)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command,
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setLong(1, nota.getAluno_id());
                stmt.setLong(2, nota.getAula_id());
                stmt.setDouble(3, nota.getValor());

                ResultSet rs = stmt.getGeneratedKeys();
                
                if(rs.next()){
                    Long id = rs.getLong(1);
                    nota.setId(id);
                    return nota;
                }
        }
        throw new RuntimeException("Erro ao cadastrar uma nova nota!");
    }
}
