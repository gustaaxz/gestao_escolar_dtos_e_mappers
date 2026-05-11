package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.model.Turma_Aluno;

public class Turma_AlunoMapper {
    public Turma_Aluno paraEntidade(
            NotaRequisicaoDTO requisicaoDTO
    ){
        return new Nota(
                requisicaoDTO.aluno_id(),
                requisicaoDTO.aula_id(),
                requisicaoDTO.valor()
        );
    }

    public NotaRespostaDTO paraRespostaDTO(
            Nota nota
    ){
        return new NotaRespostaDTO(
                nota.getId(),
                nota.getAluno_id(),
                nota.getAula_id(),
                nota.getValor()
        );
    }
}
