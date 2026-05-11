package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.turma_aluno.Turma_AlunoRequisicaoDTO;
import com.weg.gestao_escolar.dto.turma_aluno.Turma_AlunoRespostaDTO;
import com.weg.gestao_escolar.model.Turma_Aluno;

public class Turma_AlunoMapper {
    public Turma_Aluno paraEntidade(
            Turma_AlunoRequisicaoDTO requisicaoDTO
    ){
        return new Turma_Aluno(
                requisicaoDTO.turma_id(),
                requisicaoDTO.aluno_id()
        );
    }

    public Turma_AlunoRespostaDTO paraRespostaDTO(
            Turma_Aluno turma_aluno
    ){
        return new Turma_AlunoRespostaDTO(
                turma_aluno.getTurma_id(),
                turma_aluno.getAluno_id()
        );
    }
}
