package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escolar.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoRespostaDTO postAluno(@RequestBody AlunoRequisicaoDTO alunoRequisicaoDTO) {
        try {
            return alunoService.criarAluno(alunoRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<AlunoRespostaDTO> getTodosAlunos() {
        try {
            return alunoService.buscarTodosAlunos();
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AlunoRespostaDTO getAlunoPorId(@PathVariable Long id) {
        try {
            return alunoService.buscarAlunoPorId(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public AlunoRespostaDTO putAtualizarAluno(@PathVariable Long id, @RequestBody AlunoRequisicaoDTO alunoRequisicaoDTO) {
        try {
            return alunoService.atualizarAluno(id, alunoRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAlunoPorId(@PathVariable Long id) {
        try {
            alunoService.deletarAluno(id);
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
