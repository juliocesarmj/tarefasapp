package com.tarefas.domain.services.usuario;

import com.tarefas.application.dtos.authentication.AuthGetDTO;
import com.tarefas.application.dtos.authentication.AuthPostDTO;
import com.tarefas.application.dtos.usuario.UsuarioDTO;
import com.tarefas.application.dtos.usuario.UsuarioPostDTO;
import com.tarefas.domain.model.Usuario;
import com.tarefas.infrastructure.repositories.UsuarioRepository;
import com.tarefas.infrastructure.security.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
