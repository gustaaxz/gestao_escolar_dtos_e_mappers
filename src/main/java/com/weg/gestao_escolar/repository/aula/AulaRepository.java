package com.weg.gestao_escolar.repository.aula;

import java.sql.SQLException;
import java.util.List;

import com.weg.gestao_escolar.model.Aula;

public interface AulaRepository {
    Aula cadastrarAula(Aula aula) throws SQLException;
    Aula buscarAula(Long id) throws SQLException;
    List<Aula> buscarTodasAulas() throws SQLException;
    void atualizarAula(Aula aula) throws SQLException;
    void deletarAula(Long id) throws SQLException;
    boolean existePorId(Long id) throws SQLException;
}
