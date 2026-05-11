package com.weg.gestao_escolar.dto.aula;

import java.time.LocalDateTime;

public record AulaRespostaDTO(
    Long id, Long turma_id, LocalDateTime data_hora, String assunto
){
    
}
