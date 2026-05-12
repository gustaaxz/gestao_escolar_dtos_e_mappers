package com.weg.gestao_escolar.mapper;

import org.springframework.stereotype.Component;

import com.weg.gestao_escolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escolar.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escolar.model.Aula;

@Component
public class AulaMapper {
    public Aula paraEntidade(
            AulaRequisicaoDTO requisicaoDTO
    ){
        return new Aula(
                requisicaoDTO.turma_id(),
                requisicaoDTO.data_hora(),
                requisicaoDTO.assunto()
        );
    }

    public AulaRespostaDTO paraRespostaDTO(
            Aula aula
    ){
        return new AulaRespostaDTO(
                aula.getId(),
                String.valueOf(aula.getTurma_id()),
                aula.getData_hora(),
                aula.getAssunto()
        );
    }
}
