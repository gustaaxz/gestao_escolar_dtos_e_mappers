package com.weg.gestao_escolar.repository.aula;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Aula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AulaRepositoryImpl implements AulaRepository {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAula'");
    }

    @Override
    public List<Aula> buscarTodasAulas() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodasAulas'");
    }

    @Override
    public void atualizarAula(Aula aula) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarAula'");
    }

    @Override
    public void deletarAula(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarAula'");
    }
}
