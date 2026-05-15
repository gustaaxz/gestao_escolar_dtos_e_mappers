package com.weg.gestao_escolar.repository.curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.gestao_escolar.connection.ConnectionFactory;
import com.weg.gestao_escolar.model.Curso;

@Repository
public class CursoRepositoryImpl implements CursoRepository {
    public Curso cadastrarCurso(Curso curso) throws SQLException {
        String command = """
                INSERT INTO curso
                (nome, codigo)
                VALUES
                (?,?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, 
            PreparedStatement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, curso.getNome());
                stmt.setString(2, curso.getCodigo());

                ResultSet rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    Long id = rs.getLong(1);
                    curso.setId(id);
                    return curso;
                }
            }
        throw new RuntimeException("Erro ao criar um novo curso!");
    }

    @Override
    public Curso buscarCurso(Long id) throws SQLException {
        String query = """
                SELECT nome, codigo
                FROM curso
                WHERE id = ?
                """;
            
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareCall(query)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Curso curso = new Curso(
                    rs.getString("nome"),
                    rs.getString("codigo")
                );   

                return curso;
           }
        }
        throw new RuntimeException("Erro ao buscar o curso especificado!");
    }

    @Override
    public List<Curso> buscarTodosCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String query = """
                SELECT id, nome, codigo
                FROM curso
                """;
        
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                Curso curso = new Curso(id, nome, codigo);
                cursos.add(curso);
            }
        }
        return cursos;
    }

    @Override
    public void atualizarCurso(Curso curso) throws SQLException {
        String command = """
                UPDATE curso
                SET nome = ?, codigo = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setLong(3, curso.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletarCurso(Long id) throws SQLException {
        String command = """
                DELETE FROM curso
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
                    FROM curso
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
        throw new RuntimeException("Erro ao buscar se o curso existe!");
    }
}
