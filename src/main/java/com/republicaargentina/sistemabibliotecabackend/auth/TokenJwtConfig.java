package com.republicaargentina.sistemabibliotecabackend.auth;

public class TokenJwtConfig {
    public static final String SECRET_KEY = "algun_token_con_alguna_frase_secreta";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
}
