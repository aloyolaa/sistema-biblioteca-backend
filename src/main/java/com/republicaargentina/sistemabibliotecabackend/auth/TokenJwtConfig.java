package com.republicaargentina.sistemabibliotecabackend.auth;

public class TokenJwtConfig {
    public final static String SECRET_KEY = "algun_token_con_alguna_frase_secreta";
    public final static String PREFIX_TOKEN = "Bearer ";
    public final static String HEADER_AUTHORIZATION = "Authorization";
}
