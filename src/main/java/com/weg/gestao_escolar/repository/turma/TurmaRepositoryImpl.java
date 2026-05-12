package com.weg.gestao_escolar.repository.turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                stmt.executeUpdate();
            
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
        String query = """
                SELECT id, nome, curso_id, professor_id
                FROM turma
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Long idBuscado = rs.getLong("id");
                String nome = rs.getString("nome");
                Long curso_id = rs.getLong("curso_id");
                Long professor_id = rs.getLong("professor_id");

                return new Turma(idBuscado, curso_id, professor_id, nome);
            }
            throw new RuntimeException("Erro ao buscar o aluno desejado!");
        }
    }

    @Override
    public List<Turma> buscarTodasTurmas() throws SQLException {
        List<Turma> turmas = new ArrayList<>();
        String query = """
                SELECT id, nome, curso_id, professor_id
                FROM turma
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Turma turma = new Turma (
                    rs.getLong("id"),
                    rs.getLong("curso_id"),
                    rs.getLong("professor_id"),
                    rs.getString("nome")
                );
                
                turmas.add(turma);
            }
            return turmas;
        }
    }

    @Override
    public void atualizarTurma(Turma turma) throws SQLException {
        String command = """
                UPDATE turma
                SET curso_id = ?, professor_id = ?, nome = ?
                WHERE id = ?
                """;    

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, turma.getCurso_id());
            stmt.setLong(2, turma.getProfessor_id());
            stmt.setString(3, turma.getNome());
            stmt.setLong(4, turma.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletarTurma(Long id) throws SQLException {
        String command = """
                DELETE FROM turma
                WHERE id = ?
                """;
        
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
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
