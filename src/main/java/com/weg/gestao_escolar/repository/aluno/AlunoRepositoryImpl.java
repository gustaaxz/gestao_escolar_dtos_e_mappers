package com.weg.gestao_escolar.repository.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Aluno;

public class AlunoRepositoryImpl {
    public static Aluno cadastrarAluno(Aluno aluno) throws SQLException{
        String command = """
                INSERT INTO aluno
                (nome, email, matricula, data_nascimento)
                VALUES
                (?,?,?,?)
                """;
                
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, 
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, aluno.getNome());
                stmt.setString(2, aluno.getEmail());
                stmt.setDate(3, aluno.getData_nascimento());

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    aluno.setId(id);
                    return aluno;
                }
            }
        throw new RuntimeException("Erro ao cadastrar um novo aluno!");
    }
}
