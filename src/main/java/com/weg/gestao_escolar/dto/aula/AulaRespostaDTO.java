package com.weg.gestao_escolar.dto.aula;

import java.time.LocalDateTime;

public record AulaRespostaDTO(
    Long id, String nomeTurma, LocalDateTime dataHora, String assunto
){
    
}
