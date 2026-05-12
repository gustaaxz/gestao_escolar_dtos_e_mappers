package com.weg.gestao_escolar.repository.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Aluno;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {
    
    @Override
    public List<Aluno> buscarTodosAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String query = """
                SELECT id, nome, email, matricula, data_nascimento
                FROM aluno
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Aluno aluno = new Aluno (
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("matricula"),
                    rs.getDate("data_nascimento")
                );
                
                alunos.add(aluno);
            }
            return alunos;
        }
    }
    
    @Override
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
                stmt.setString(3, aluno.getMatricula());
                stmt.setDate(4, aluno.getData_nascimento());
                stmt.executeUpdate();

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
        String query = """
                SELECT id, nome, email, matricula, data_nascimento
                FROM aluno
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Long idBuscado = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                Date data_nascimento = rs.getDate("data_nascimento");

                return new Aluno(idBuscado, nome, email, matricula, data_nascimento);
            }
            throw new RuntimeException("Erro ao buscar o aluno desejado!");
        }
    }

    @Override
    public void atualizarAluno(Aluno aluno) throws SQLException {
        String command = """
                UPDATE aluno
                SET nome = ?, email = ?, matricula = ?, data_nascimento = ?
                WHERE id = ?
                """;    

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, aluno.getData_nascimento());
            stmt.setLong(5, aluno.getId());
            stmt.executeUpdate();

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas == 0) {
                throw new RuntimeException("Erro ao atualizar o aluno especificado!");
            } 
        }
    }

    @Override
    public void deletarAluno(Long id) throws SQLException {
        String command = """
                DELETE FROM aluno
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
                    FROM aluno
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
        throw new RuntimeException("Erro ao buscar se o aluno existe!");
    }
}
