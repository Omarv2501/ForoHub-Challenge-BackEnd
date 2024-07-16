package com.vasquez.controller;

import com.vasquez.domain.usuario.DatosAutenticationUsuario;
import com.vasquez.infra.security.DatosJWTToken;
import com.vasquez.infra.security.TokenService;
import jakarta.validation.Valid;
import com.vasquez.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager autenticacion;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticationUsuario datos){
        Authentication authtoken = new UsernamePasswordAuthenticationToken(
                datos.login(),
                datos.clave()
        );

        var usuarioAutenticado = this.autenticacion.authenticate(authtoken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
