package com.weg.gestao_escolar.repository.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.*;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Professor;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {
    public Professor cadastrarProfessor(Professor professor) throws SQLException{
        String command = """
                INSERT INTO professor
                (nome, email, disciplina)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command,
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, professor.getNome());
                stmt.setString(2, professor.getEmail());
                stmt.setString(3, professor.getDisciplina());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    professor.setId(id);
                    return professor;
                }
            }
        throw new RuntimeException("Erro ao criar um novo Professor!");
    }

    @Override
    public Professor buscarProfessor(Long id) throws SQLException {
        String query = """
                SELECT nome, email, disciplina
                FROM professor
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                Professor professor = new Professor(nome, email, disciplina);
                return professor;
            }
        }
        throw new RuntimeException("Erro ao buscar o professor especificado!");
    }

    @Override
    public List<Professor> buscarTodosProfessores() throws SQLException {
        List<Professor> professores = new ArrayList<>();
        String query = """
                SELECT id, nome, email, disciplina
                FROM professor
                """;
            
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Professor professor = new Professor(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("disciplina")
                );
                professores.add(professor);
            }
        }
        return professores;
    }

    @Override
    public Professor atualizarProfessor(Professor professor) throws SQLException {
        String command = """
                UPDATE professor
                SET nome = ?, email = ?, disciplina = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setLong(4, professor.getId());
            stmt.executeUpdate();
        }
        return professor;
    }

    @Override
    public void deletarProfessor(Long id) throws SQLException {
        String command = """
                DELETE FROM professor
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
                    FROM professor
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
        throw new RuntimeException("Erro ao buscar se o professor existe!");
    }
}
