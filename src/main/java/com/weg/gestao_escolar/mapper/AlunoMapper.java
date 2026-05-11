package com.weg.gestao_escolar.mapper;

import org.springframework.stereotype.*;

import com.weg.gestao_escolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escolar.model.Aluno;

@Component
public class AlunoMapper {
    public Aluno paraEntidade(
            AlunoRequisicaoDTO requisicaoDTO
    ){
        return new Aluno(
                requisicaoDTO.nome(),
                requisicaoDTO.email(),
                requisicaoDTO.matricula(),
                requisicaoDTO.data_nascimento()

        );
    }

    public AlunoRespostaDTO paraRespostaDTO(
            Aluno aluno
    ){
        return new AlunoRespostaDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getData_nascimento()
        );
    }
}
