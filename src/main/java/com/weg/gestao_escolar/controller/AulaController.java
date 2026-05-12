package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escolar.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escolar.model.Aula;
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
            AulaRespostaDTO respostaDTO = aulaService.cadastrarAula(aulaRequisicaoDTO);
            return respostaDTO;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Aula> getTodasAulas() {
        try {
            List<Aula> aulas = aulaService.buscarTodasAulas();
            return aulas;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Aula getAlunoPorId(@PathVariable Long id) {
        try {
            Aula aula = aulaService.buscarAulaPorId(id);
            return aula;
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Aula putAtualizarAula(@PathVariable Long id, @RequestBody Aula aula) {
        try {
            aulaService.atualizarAula(id, aula);
            return aula;
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
