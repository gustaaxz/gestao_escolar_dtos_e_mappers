package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.nota.NotaRequisicaoDTO;
import com.weg.gestao_escolar.dto.nota.NotaRespostaDTO;
import com.weg.gestao_escolar.model.Nota;

public class NotaMapper {
    public Nota paraEntidade(
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
                String.valueOf(nota.getAluno_id()),
                String.valueOf(nota.getAula_id()),
                nota.getValor()
        );
    }
}
