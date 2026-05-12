package com.weg.gestao_escolar.mapper;

import org.springframework.stereotype.*;

import com.weg.gestao_escolar.model.Professor;
import com.weg.gestao_escolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escolar.dto.professor.ProfessorRespostaDTO;

@Component
public class ProfessorMapper {
    public Professor paraEntidade(
            ProfessorRequisicaoDTO requisicaoDTO
    ){
        return new Professor(
                requisicaoDTO.nome(),
                requisicaoDTO.email(),
                requisicaoDTO.disciplina()
        );
    }

    public ProfessorRespostaDTO paraRespostaDTO(
            Professor professor
    ){
        return new ProfessorRespostaDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }
}
