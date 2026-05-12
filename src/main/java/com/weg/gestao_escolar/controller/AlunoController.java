package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escolar.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escolar.model.Aluno;
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
            AlunoRespostaDTO respostaDTO = alunoService.criarAluno(alunoRequisicaoDTO);
            return respostaDTO;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Aluno> getTodosAlunos() {
        try {
            List<Aluno> alunos = alunoService.buscarTodosAlunos();
            return alunos;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Aluno getAlunoPorId(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.buscarAlunoPorId(id);
            return aluno;
        }
        catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Aluno putAtualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        try {
            alunoService.atualizarAluno(id, aluno);
            return aluno;
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
