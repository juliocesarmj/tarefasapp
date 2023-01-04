package com.tarefas.domain.services.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tarefas.application.dtos.AuthGetDTO;
import com.tarefas.application.dtos.AuthPostDTO;
import com.tarefas.application.dtos.UsuarioDTO;
import com.tarefas.application.dtos.UsuarioPostDTO;
import com.tarefas.domain.infrastructure.UsuarioRepository;
import com.tarefas.domain.infrastructure.security.TokenAuthenticationService;
import com.tarefas.domain.model.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final TokenAuthenticationService tokenAuthenticationService;
	
	@Override
	public UsuarioDTO novoUsuario(UsuarioPostDTO dto) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(getHashMd5(dto.getSenha()));
		
		try {
			usuarioRepository.save(usuario);
			return new UsuarioDTO(usuario.getNome(), usuario.getEmail());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getHashMd5(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
		return hash.toString(16);
	}

	@Override
	public AuthGetDTO auth(AuthPostDTO dto) {
		Optional<Usuario> usuarioOptional = this.usuarioRepository
				.findByEmailAndSenha(dto.getEmail(), getHashMd5(dto.getSenha()));
		
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			 return new AuthGetDTO(usuario.getNome(),
					usuario.getEmail(),
					this.tokenAuthenticationService.generateToken(usuario.getEmail(), "ROLE_USUARIO"));
		}
		
		throw new IllegalArgumentException("Usuario ou senha inválidos.");
	}
}
