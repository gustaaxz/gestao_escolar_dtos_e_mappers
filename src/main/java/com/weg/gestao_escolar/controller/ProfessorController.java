package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escolar.dto.professor.ProfessorRespostaDTO;
import com.weg.gestao_escolar.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;   
    }

    @PostMapping
    public ProfessorRespostaDTO postProfessor(@RequestBody ProfessorRequisicaoDTO professorRequisicaoDTO) {
        try {
            return professorService.cadastrarProfessor(professorRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<ProfessorRespostaDTO> getTodosProfessores() {
        try {
            return professorService.buscarTodosProfessores();
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ProfessorRespostaDTO getProfessorPorId(@PathVariable Long id) {
        try {
            return professorService.buscarProfessorPorId(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ProfessorRespostaDTO putAtualizarProfessor(@PathVariable Long id, @RequestBody ProfessorRequisicaoDTO professorRequisicaoDTO) {
        try {
            return professorService.atualizarProfessor(id, professorRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProfessorPorId(@PathVariable Long id) {
        try {
            professorService.deletarProfessor(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
