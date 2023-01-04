package com.tarefas.domain.services.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.tarefas.application.dtos.UsuarioDTO;
import com.tarefas.application.dtos.UsuarioPostDTO;
import com.tarefas.domain.infrastructure.UsuarioRepository;
import com.tarefas.domain.model.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
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
}
