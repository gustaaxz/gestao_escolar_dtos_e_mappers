package com.weg.gestao_escolar.dto.aluno;

import java.sql.Date;

public record AlunoRespostaDTO(
    Long id, 
    String nome, 
    String email, 
    String matricula, 
    Date data_nascimento
){
    
}
