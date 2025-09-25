package com.smarttmt.utilities;

import com.smarttmt.excepciones.RecursoNoEncontradoException;
import io.jsonwebtoken.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class TokenAutenticacion {

    private static final String KEY_JWT = "NDjHVrKTgkrJYkn380Ye5kY2bCt8r8HBX0vVEy4z028=";
    static Logger logger = LoggerFactory.getLogger(TokenAutenticacion.class);
    static final String KEY_TERCERO_CLIENTE = "terclie";
    static final String KEY_PERSONAL = "personal";

    private TokenAutenticacion() {
        //constructor implicito por defecto
    }

    public static String getHexByte(String cadena, String hexByte) {
        return (switch (hexByte) {
            case "1" -> DigestUtils.sha1Hex(cadena);
            case "384" -> DigestUtils.sha384Hex(cadena);
            case "512" -> DigestUtils.sha512Hex(cadena);
            default -> DigestUtils.sha256Hex(cadena);
        });
    }

    public static String generaTokenJWT(String usuario, String codigoCliente, String personal, String idToken) {
        logger.info("generaTokenJWT");
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        try {
            return Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, KEY_JWT)//clave del token
                    .setSubject(usuario)// usuario a quien se asigna el token
                    .setId(getHexByte(idToken + Utilidades.getSysdate("ddMMyyyy"), "256"))//id del token armado por el parametro IDTOPRWS y fecha actual en formato ddMMyyyy
                    .setIssuedAt(java.sql.Date.valueOf(today))//fecha de creacion del token
                    .setExpiration(java.sql.Date.valueOf(tomorrow))//fecha de expiracion del token
                    .claim(KEY_TERCERO_CLIENTE, codigoCliente)//parametro adicion codigo del cliente
                    .claim(KEY_PERSONAL, personal).compact();//parametro adicion descripcion del personal autenticado
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RecursoNoEncontradoException("No se genero el token");
        }
    }

    private static Claims getClaims(String token){
         return Jwts.parserBuilder().setSigningKey(KEY_JWT).build().parseClaimsJws(token).getBody();
    }

    public static boolean validarToken(String token, String cliente, String idToken) {
        logger.info("init validarToken");
        try {
            token = Optional.ofNullable(token).filter(tok -> !StringUtils.isEmpty(tok)).orElseThrow(() -> new IllegalArgumentException("Es requerido el parametro token"));
            String signAlgorithm = getHexByte(idToken + Utilidades.getSysdate("ddMMyyyy"), "256");
            Claims claims = getClaims(token);
            return (signAlgorithm.equals(claims.getId()) && claims.getIssuedAt().before(claims.getExpiration()) && cliente.equals(claims.get(KEY_TERCERO_CLIENTE, String.class)));
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RecursoNoEncontradoException("No se encotro el token correcto");
        }
    }

    public static String getUsuarioToken(String token) {
        logger.info("init getUsuarioToken");
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RecursoNoEncontradoException("No se encontro el usuario en el token");
        }
    }

    public static String getPersonalToken(String token) {
        try {
            logger.info("getPersonalToken validando token inicio");
            Claims claims = getClaims(token);
            return claims.get(KEY_PERSONAL, String.class);
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RecursoNoEncontradoException("No se encontro  nombre del personal en el token");
        }
    }

}
