package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicosService service;
    @Transactional
    @PostMapping
    public ResponseEntity crearTopico(@RequestBody @Valid DatosCrearTopico datosTopico, UriComponentsBuilder uriComponentsBuilder){
        var detalleTopico = service.crear(datosTopico);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(detalleTopico.id()).toUri();
        return ResponseEntity.created(uri).body(detalleTopico);
    }
    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopicos>> obtenerTopicos(@PageableDefault(size = 10,sort = "fechaCreacion") Pageable paginacion){
        var page = service.obtenerTopicos(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopico(@PathVariable Long id){
        var topico = service.obtenerTopico(id);
        return ResponseEntity.ok(topico);
    }
    @Transactional
    @PutMapping()
    public ResponseEntity<DatosDetalleTopico> editarTopico(@RequestBody DatosEditarTopico datos){
        var topico = service.editarTopico(datos);
        return ResponseEntity.ok(topico);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        service.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }


}
