package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escolar.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escolar.model.Turma;
import com.weg.gestao_escolar.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaRespostaDTO cadastrarTurma(@RequestBody TurmaRequisicaoDTO turmaRequisicaoDTO) {
        try {
            TurmaRespostaDTO respostaDTO = turmaService.cadastrarTurma(turmaRequisicaoDTO);
            return respostaDTO;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Turma> buscarTodasTurmas() {
        try {
            List<Turma> turmas = turmaService.buscarTodasTurmas();
            return turmas;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Turma buscarTurmaPorId(@PathVariable Long id) {
        try {
            Turma turma = turmaService.buscarTurmaPorId(id);
            return turma;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Turma atualizarTurma(@RequestBody Turma turma, @PathVariable Long id) {
        try {
            turmaService.atualizarTurma(id, turma);
            return turma;
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarTurma(@PathVariable Long id){
        try {
            turmaService.deletarTurma(id);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
