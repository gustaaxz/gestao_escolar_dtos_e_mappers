package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escolar.model.Turma;

public class TurmaMapper {
    public Turma paraEntidade(
            TurmaRequisicaoDTO requisicaoDTO
    ){
        return new Turma(
                requisicaoDTO.curso_id(),
                requisicaoDTO.professor_id(),
                requisicaoDTO.nome()
        );
    }

    public TurmaRespostaDTO paraRespostaDTO(
            Turma turma 
    ){
        return new TurmaRespostaDTO(
                turma.getId(),
                turma.getCurso_id(),
                turma.getProfessor_id(),
                turma.getNome()
        );
    }
}
