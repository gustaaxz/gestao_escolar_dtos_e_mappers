package com.weg.gestao_escolar.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.gestao_escolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escolar.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escolar.mapper.AulaMapper;
import com.weg.gestao_escolar.model.Aula;
import com.weg.gestao_escolar.repository.aula.AulaRepository;

@Service
public class AulaService {
    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;

    public AulaService(AulaRepository aulaRepository, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.aulaMapper = aulaMapper;  
    }

    public AulaRespostaDTO cadastrarAula(AulaRequisicaoDTO aulaRequisicaoDTO) throws SQLException {
        Aula aula = aulaMapper.paraEntidade(aulaRequisicaoDTO);
        aulaRepository.cadastrarAula(aula);
        return aulaMapper.paraRespostaDTO(aula);
    }

    public List<AulaRespostaDTO> buscarTodasAulas() throws SQLException {
        List<Aula> aulas = aulaRepository.buscarTodasAulas();
        return aulas.stream().map(aulaMapper::paraRespostaDTO).toList();
    }

    public AulaRespostaDTO buscarAulaPorId(Long id) throws SQLException {
        Aula aula = aulaRepository.buscarAula(id);
        return aulaMapper.paraRespostaDTO(aula);
    }

    public AulaRespostaDTO atualizarAula(Long id, AulaRequisicaoDTO aulaRequisicaoDTO) throws SQLException {
        if(!aulaRepository.existePorId(id)) {
            throw new RuntimeException("Aula não encontrada!");
        }

        Aula aula = aulaMapper.paraEntidade(aulaRequisicaoDTO);
        aula.setId(id);
        aulaRepository.atualizarAula(aula);
        return aulaMapper.paraRespostaDTO(aula);
    }

    public void deletarAula(Long id) throws SQLException {
        if(!aulaRepository.existePorId(id)) {
            throw new RuntimeException("Aula não encontrada!");
        }

        aulaRepository.deletarAula(id);
    }
}
