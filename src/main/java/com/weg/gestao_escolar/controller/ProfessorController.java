package com.weg.gestao_escolar.controller;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escolar.dto.professor.ProfessorRespostaDTO;
import com.weg.gestao_escolar.model.Professor;
import com.weg.gestao_escolar.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;   
    }

    @PostMapping
    public ProfessorRespostaDTO postAluno(@RequestBody ProfessorRequisicaoDTO professorRequisicaoDTO) {
        try {
            ProfessorRespostaDTO respostaDTO = professorService.cadastrarProfessor(professorRequisicaoDTO);
            return respostaDTO;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Professor> getTodosProfessores() {
        try {
            List<Professor> professores = professorService.buscarTodosProfessores();
            return professores;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Professor getProfessorPorId(@PathVariable Long id) {
        try {
            Professor professor = professorService.buscarProfessorPorId(id);
            return professor;
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Professor putAtualizarAluno(@PathVariable Long id, @RequestBody Professor professor) {
        try {
            professorService.atualizarProfessor(id, professor);
            return professor;
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAlunoPorId(@PathVariable Long id) {
        try {
            professorService.deletarProfessor(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
