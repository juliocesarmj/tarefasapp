package com.tarefas.domain.services.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tarefas.application.dtos.authentication.AuthGetDTO;
import com.tarefas.application.dtos.authentication.AuthPostDTO;
import com.tarefas.application.dtos.usuario.UsuarioDTO;
import com.tarefas.application.dtos.usuario.UsuarioPostDTO;
import com.tarefas.domain.model.Usuario;
import com.tarefas.domain.services.exceptions.EntidadeException;
import com.tarefas.infrastructure.repositories.UsuarioRepository;
import com.tarefas.infrastructure.security.TokenAuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final TokenAuthenticationService tokenAuthenticationService;

	@Override
	public UsuarioDTO novoUsuario(UsuarioPostDTO dto) {
		validaEmail(dto.getEmail());
		Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), getHashMd5(dto.getSenha()));
		usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario.getNome(), usuario.getEmail());
	}
	
	private void validaEmail(String email) {
		Optional<Usuario> optional = this.usuarioRepository.findByEmail(email);
		if(optional.isPresent())
			throw new EntidadeException("Email já existe");
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
		Usuario usuario = getUsuario(dto.getEmail(), dto.getSenha());
		String email = usuario.getEmail();
		return new AuthGetDTO(usuario.getNome(), email,
				this.tokenAuthenticationService.generateToken(email, "ROLE_USUARIO"));

	}

	private Usuario getUsuario(String email, String senha) {
		return this.usuarioRepository.findByEmailAndSenha(email, getHashMd5(senha))
				.orElseThrow(() -> new IllegalArgumentException("Usuario ou senha inválidos"));

	}
}
