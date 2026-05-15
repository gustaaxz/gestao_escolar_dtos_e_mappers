package com.weg.gestao_escolar.repository.aula;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Aula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AulaRepositoryImpl implements AulaRepository {
    
    @Override
    public List<Aula> buscarTodasAulas() throws SQLException {
        List<Aula> aulas = new ArrayList<>();
        String query = """
                SELECT id, turma_id, data_hora, assunto
                FROM aula
                """;
        
            try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()){
                        Aula aula = new Aula(
                            rs.getLong("id"),
                            rs.getLong("turma_id"),
                            (LocalDateTime) rs.getObject("data_hora"),
                            rs.getString("assunto")
                        );
                        aulas.add(aula);
                    }   
                }
            return aulas;
    }
    
    @Override
    public Aula cadastrarAula(Aula aula) throws SQLException {
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
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    aula.setId(id);
                    return aula;
                }
            }
        throw new RuntimeException("Erro ao criar uma nova aula!");
    }

    @Override
    public Aula buscarAula(Long id) throws SQLException {
        String query = """
                SELECT id, turma_id, data_hora, assunto
                FROM aula
                WHERE id = ? 
                """;    
        
                try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){
                    stmt.setLong(1, id);

                    ResultSet rs = stmt.executeQuery();

                    if(rs.next()){
                        Long idBuscado = rs.getLong("id");
                        Long turma_id = rs.getLong("turma_id");
                        LocalDateTime data_hora = (LocalDateTime) rs.getObject("data_hora");
                        String assunto = rs.getString("assunto");

                        Aula aula = new Aula(idBuscado, turma_id, data_hora, assunto);
                        return aula;
                    }
                }
            throw new RuntimeException("Erro ao buscar a aula especificada!");
    }

    @Override
    public void atualizarAula(Aula aula) throws SQLException {
        String command = """
                UPDATE aula
                SET turma_id = ?, data_hora = ?, assunto = ?
                WHERE id = ?
                """;
        
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, aula.getTurma_id());
            stmt.setObject(2, aula.getData_hora());
            stmt.setString(3, aula.getAssunto());
            stmt.setLong(4, aula.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletarAula(Long id) throws SQLException {
        String command = """
                DELETE FROM aula
                WHERE id = ?
                """;
        
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas == 0) {
                throw new RuntimeException("Erro ao atualizar a aula especificada!");
            }  
        }
    }

    @Override
    public boolean existePorId(Long id) throws SQLException{
        String query = """
                    SELECT COUNT(0) AS resultado
                    FROM aula
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
        throw new RuntimeException("Erro ao buscar se a aula existe!");
    }
}