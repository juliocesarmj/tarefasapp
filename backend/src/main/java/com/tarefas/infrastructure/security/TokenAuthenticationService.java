package com.tarefas.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenAuthenticationService {
	
	@Value("${jwt.application}")
	private String application;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	public String generateToken(String user, String roles) {
		Date now = new Date();
		Date expirationToken = new Date(now.getTime() + Long.parseLong(this.expiration));
		
//		return Jwts.builder()
//				.setIssuer(this.application)
//				.setSubject(user)
//				.setExpiration(exp)
//				.signWith(SignatureAlgorithm.HS256, this.secret)
//				.compact();
		
		//PERFIS DE ACESSO QUE O USUÁRIO TEM. isso pode vir como parametro deste método gerenateToken(String user, String roles)
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		//gerando o token
		return Jwts.builder()
				.setId(this.application) // nome da aplicacao
				.setSubject(user) // identificacao de quem está acessando o sistema.
				.claim("authorities", 
						grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(now) // data geracao token
				.setExpiration(expirationToken)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();		
	}
}
