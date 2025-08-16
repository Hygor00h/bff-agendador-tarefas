package com.javanauta.bff_agendador.controller;


import com.javanauta.bff_agendador.business.UsuarioService;
import com.javanauta.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendador.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login e usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salva Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200",description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400",description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Login do usuário")
    @ApiResponse(responseCode = "200",description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401",description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email",
            description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200",description = "Usuário encontrado")
    @ApiResponse(responseCode = "404",description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email")String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuário por Id", description = "Deleta usuário")
    @ApiResponse(responseCode = "200",description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuário", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200",description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuário", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200",description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuário", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200",description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuário", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200",description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuário", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200",description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto));
    }
}
