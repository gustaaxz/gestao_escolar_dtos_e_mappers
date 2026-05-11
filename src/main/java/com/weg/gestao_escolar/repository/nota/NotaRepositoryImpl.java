package com.weg.gestao_escolar.repository.nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Nota;

@Repository
public class NotaRepositoryImpl implements NotaRepository {
    public Nota cadastrarNota(Nota nota) throws SQLException {
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

    @Override
    public Nota buscarNota(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarNota'");
    }

    @Override
    public List<Nota> buscarTodasNotas() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodasNotas'");
    }

    @Override
    public void atualizarNota(Nota nota) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarNota'");
    }

    @Override
    public void deletarNota(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarNota'");
    }
}
