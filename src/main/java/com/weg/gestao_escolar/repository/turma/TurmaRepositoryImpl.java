package com.weg.gestao_escolar.repository.turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Turma;

@Repository
public class TurmaRepositoryImpl implements TurmaRepository {
    public Turma cadastrarTurma(Turma turma) throws SQLException {
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

    @Override
    public Turma buscarTurma(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTurma'");
    }

    @Override
    public List<Turma> buscarTodasTurmas() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodasTurmas'");
    }

    @Override
    public void atualizarTurma(Turma turma) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarTurma'");
    }

    @Override
    public void deletarTurma(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarTurma'");
    }

    public boolean existePorId(Long id) throws SQLException{
        String query = """
                    SELECT COUNT(0) AS resultado
                    FROM turma
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
        throw new RuntimeException("Erro ao buscar se a turma existe!");
    }
}
