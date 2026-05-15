package com.weg.gestao_escolar.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.*;

import com.weg.gestao_escolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escolar.dto.professor.ProfessorRespostaDTO;
import com.weg.gestao_escolar.mapper.ProfessorMapper;
import com.weg.gestao_escolar.model.Professor;
import com.weg.gestao_escolar.repository.professor.ProfessorRepository;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    
    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorRespostaDTO cadastrarProfessor(ProfessorRequisicaoDTO professorRequisicaoDTO) throws SQLException {
        Professor professor = professorMapper.paraEntidade(professorRequisicaoDTO);
        professorRepository.cadastrarProfessor(professor);
        return professorMapper.paraRespostaDTO(professor);
    }

    public List<ProfessorRespostaDTO> buscarTodosProfessores() throws SQLException {
        List<Professor> professores = professorRepository.buscarTodosProfessores();
        return professores.stream().map(professorMapper::paraRespostaDTO).toList();
    }

    public ProfessorRespostaDTO buscarProfessorPorId(Long id) throws SQLException {
        Professor professor = professorRepository.buscarProfessor(id);
        return professorMapper.paraRespostaDTO(professor);
    }

    public ProfessorRespostaDTO atualizarProfessor(Long id, ProfessorRequisicaoDTO professorRequisicaoDTO) throws SQLException {
        if(!professorRepository.existePorId(id)) {
            throw new RuntimeException("Professor não encontrado!");
        }

        Professor professor = professorMapper.paraEntidade(professorRequisicaoDTO);
        professor.setId(id);
        professorRepository.atualizarProfessor(professor);
        return professorMapper.paraRespostaDTO(professor);
    }

    public void deletarProfessor(Long id) throws SQLException {
        if(!professorRepository.existePorId(id)) {
            throw new RuntimeException("Professor não encontrado!");
        }

        professorRepository.deletarProfessor(id);
    }
}
