package com.weg.gestao_escolar.repository.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Aluno;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {
    public Aluno cadastrarAluno(Aluno aluno) throws SQLException{
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

    @Override
    public Aluno buscarAluno(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarALuno'");
    }

    @Override
    public List<Aluno> buscarTodosAlunos() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodosAlunos'");
    }

    @Override
    public void atualizarAluno(Aluno aluno) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarAluno'");
    }

    @Override
    public void deletarAluno(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarAluno'");
    }
}
