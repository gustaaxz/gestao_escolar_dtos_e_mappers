package com.weg.gestao_escolar.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.gestao_escolar.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escolar.dto.turma.TurmaRespostaDTO;
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
            return turmaService.cadastrarTurma(turmaRequisicaoDTO);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<TurmaRespostaDTO> buscarTodasTurmas() {
        try {
            return turmaService.buscarTodasTurmas();
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public TurmaRespostaDTO buscarTurmaPorId(@PathVariable Long id) {
        try {
            return turmaService.buscarTurmaPorId(id);
        }
        catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public TurmaRespostaDTO atualizarTurma(@PathVariable Long id, @RequestBody TurmaRequisicaoDTO turmaRequisicaoDTO) {
        try {
            return turmaService.atualizarTurma(id, turmaRequisicaoDTO);
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
