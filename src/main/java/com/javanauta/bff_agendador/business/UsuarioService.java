package com.javanauta.bff_agendador.business;


import com.javanauta.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendador.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return client.salvaUsuario(usuarioDTO);
    }

   public String loginUsuario(LoginRequestDTO usuarioDTO){
        return client.login(usuarioDTO);
   }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletaUsuarioPorEmail(String email, String token){
        client.deleteUsuarioPorEmail(email, token);
    }

    //ternario

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
        return client.atualizaDadoUsuario(usuarioDTO, token);
    }

    //atualizando via id

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    //cadastro de novos endereços

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
        return client.cadastraTelefone(dto, token);
    }
}
