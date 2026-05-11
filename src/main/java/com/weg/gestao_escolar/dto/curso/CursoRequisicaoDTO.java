package com.weg.gestao_escolar.dto.curso;

import java.util.List;

public record CursoRequisicaoDTO(
    String nome, 
    String codigo,
    List<Long> listaProfessorIds
){
    
}
