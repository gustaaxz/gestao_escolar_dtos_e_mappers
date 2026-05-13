package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escolar.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escolar.service.AulaService;

@RestController
@RequestMapping("/aula")
public class AulaController {
    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;   
    }

    @PostMapping
    public AulaRespostaDTO postAula(@RequestBody AulaRequisicaoDTO aulaRequisicaoDTO) {
        try {
            return aulaService.cadastrarAula(aulaRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<AulaRespostaDTO> getTodasAulas() {
        try {
            return aulaService.buscarTodasAulas();
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AulaRespostaDTO getAulaPorId(@PathVariable Long id) {
        try {
            return aulaService.buscarAulaPorId(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public AulaRespostaDTO putAtualizarAula(@PathVariable Long id, @RequestBody AulaRequisicaoDTO aulaRequisicaoDTO) {
        try {
            return aulaService.atualizarAula(id, aulaRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAulaPorId(@PathVariable Long id) {
        try {
            aulaService.deletarAula(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
