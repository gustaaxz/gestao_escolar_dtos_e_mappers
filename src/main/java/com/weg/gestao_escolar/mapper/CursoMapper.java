package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.curso.CursoRequisicaoDTO;
import com.weg.gestao_escolar.dto.curso.CursoRespostaDTO;
import com.weg.gestao_escolar.model.Curso;
import com.weg.gestao_escolar.model.Professor;

import java.util.List;
import java.util.stream.Collectors;

public class CursoMapper {
        public Curso paraEntidade(
                        CursoRequisicaoDTO requisicaoDTO) {
                return new Curso(
                        requisicaoDTO.nome(),
                        requisicaoDTO.codigo());
        }       
        public CursoRespostaDTO paraRespostaDTO(
                Curso curso, List<Professor> professores) {
                
                String nomesProfessores = "";
                if (professores != null && !professores.isEmpty()) {
                        nomesProfessores = professores.stream()
                                .map(Professor::getNome)
                                .collect(Collectors.joining(", "));
                }

                return new CursoRespostaDTO(
                        curso.getId(),
                        curso.getNome(),
                        curso.getCodigo(),
                        nomesProfessores
                );
        }
}