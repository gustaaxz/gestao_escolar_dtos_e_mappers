package com.weg.gestao_escolar.mapper;

import org.springframework.stereotype.*;

import com.weg.gestao_escolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escolar.model.Turma;

@Component
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
                turma.getNome(),
                String.valueOf(turma.getCurso_id()),
                String.valueOf(turma.getProfessor_id()),
                ""
        );
    }
}
