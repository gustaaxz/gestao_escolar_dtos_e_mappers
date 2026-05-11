package com.weg.gestao_escolar.repository.nota;

import java.sql.SQLException;
import java.util.List;

import com.weg.gestao_escolar.model.Nota;

public interface NotaRepository {
    Nota cadastrarNota(Nota nota) throws SQLException;
    Nota buscarNota(Long id) throws SQLException;
    List<Nota> buscarTodasNotas() throws SQLException;
    void atualizarNota(Nota nota) throws SQLException;
    void deletarNota(Long id) throws SQLException;
}
