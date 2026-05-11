package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.curso.CursoRequisicaoDTO;
import com.weg.gestao_escolar.dto.curso.CursoRespostaDTO;
import com.weg.gestao_escolar.model.Curso;

public class CursoMapper {
    public Curso paraEntidade(
            CursoRequisicaoDTO requisicaoDTO
    ){
        return new Curso(
                requisicaoDTO.nome(),
                requisicaoDTO.codigo()
        );
    }

    public CursoRespostaDTO paraRespostaDTO(
            Curso curso
    ){
        return new CursoRespostaDTO(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }
}