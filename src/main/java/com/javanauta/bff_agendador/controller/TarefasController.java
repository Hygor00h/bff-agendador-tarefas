package com.javanauta.bff_agendador.controller;


import com.javanauta.bff_agendador.business.TarefasService;
import com.javanauta.bff_agendador.business.dto.in.TarefasDTORequest;
import com.javanauta.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bff_agendador.business.enums.StatusNotificacaoEnum;
import com.javanauta.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salva tarefas de Usuário", description = "Cria um novo tarefa")
    @ApiResponse(responseCode = "200",description = "Tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/evento")
    @Operation(summary = "Busca tarefas por Período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200",description = "Tarefa encontradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorEvento(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datafinal,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, datafinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usuário", description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200",description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID", description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200",description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200",description = "Status da tarefas alteradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader("Authorization")String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Busca dados de tarefas", description = "Busca dados das tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200",description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader("Authorizantion")String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
