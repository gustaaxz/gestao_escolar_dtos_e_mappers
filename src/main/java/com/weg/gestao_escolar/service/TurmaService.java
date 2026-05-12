package com.weg.gestao_escolar.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.gestao_escolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escolar.mapper.TurmaMapper;
import com.weg.gestao_escolar.model.Turma;
import com.weg.gestao_escolar.repository.turma.TurmaRepository;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;

    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
    }

    public TurmaRespostaDTO cadastrarTurma(TurmaRequisicaoDTO turmaRequisicaoDTO) throws SQLException {
        Turma turma = turmaMapper.paraEntidade(turmaRequisicaoDTO);
        turmaRepository.cadastrarTurma(turma);
        return turmaMapper.paraRespostaDTO(turma);
    }

    public List<Turma> buscarTodasTurmas() throws SQLException {
        List<Turma> turmas = turmaRepository.buscarTodasTurmas();
        return turmas;
    }

    public Turma buscarTurmaPorId(Long id) throws SQLException {
        Turma turma = turmaRepository.buscarTurma(id);
        return turma;
    }

    public Turma atualizarTurma(Long id, Turma turma) throws SQLException {
        if(!turmaRepository.existePorId(id)){
            throw new RuntimeException("Turma não encontrada!");
        }

        turma.setId(id);
        turmaRepository.atualizarTurma(turma);
        return turma;
    }

    public void deletarTurma(Long id) throws SQLException {
        if(!turmaRepository.existePorId(id)){
            throw new RuntimeException("Turma não encontrada!");
        }

        turmaRepository.deletarTurma(id);
    }
}
