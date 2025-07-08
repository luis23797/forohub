package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.DatosAuthenticacion;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.infra.security.DatosTokenJWT;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAuthenticacion datosAuthenticacion){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAuthenticacion.nombre(),datosAuthenticacion.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return  ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

    @GetMapping
    public ResponseEntity test(@RequestBody @Valid DatosAuthenticacion datosAuthenticacion){
        var usuario = repository.findByNombre("luis");
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAuthenticacion.nombre(),datosAuthenticacion.contrasena());
        System.out.println(authenticationToken);
//        var autenticacion = manager.authenticate(authenticationToken);
        return  ResponseEntity.ok(datosAuthenticacion);
    }

}
