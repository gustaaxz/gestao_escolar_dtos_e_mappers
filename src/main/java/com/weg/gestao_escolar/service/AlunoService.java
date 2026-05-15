package com.weg.gestao_escolar.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.gestao_escolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escolar.mapper.AlunoMapper;
import com.weg.gestao_escolar.model.Aluno;
import com.weg.gestao_escolar.repository.aluno.AlunoRepository;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;  
    }

    public AlunoRespostaDTO criarAluno(AlunoRequisicaoDTO alunoRequisicaoDTO) throws SQLException {
        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicaoDTO);
        alunoRepository.cadastrarAluno(aluno);
        return alunoMapper.paraRespostaDTO(aluno);
    }

    public List<AlunoRespostaDTO> buscarTodosAlunos() throws SQLException {
        List<Aluno> alunos = alunoRepository.buscarTodosAlunos();
        return alunos.stream()
            .map(alunoMapper::paraRespostaDTO)
            .toList();
    }

    public AlunoRespostaDTO buscarAlunoPorId(Long id) throws SQLException {
        Aluno aluno = alunoRepository.buscarAluno(id);
        return alunoMapper.paraRespostaDTO(aluno);
    }

    public AlunoRespostaDTO atualizarAluno(Long id, AlunoRequisicaoDTO alunoRequisicaoDTO) throws SQLException {
        if(!alunoRepository.existePorId(id)) {
            throw new RuntimeException("Aluno não encontrado!");
        }

        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicaoDTO);
        aluno.setId(id);
        alunoRepository.atualizarAluno(aluno);
        return alunoMapper.paraRespostaDTO(aluno);
    }

    public void deletarAluno(Long id) throws SQLException {
        if(!alunoRepository.existePorId(id)) {
            throw new RuntimeException("Aluno não encontrado!");
        }

        alunoRepository.deletarAluno(id);
    }
}
