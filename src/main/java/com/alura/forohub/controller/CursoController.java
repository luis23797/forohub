package com.alura.forohub.controller;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.curso.DatosCrearCurso;
import com.alura.forohub.domain.curso.DatosDetalleCurso;
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
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity crearCurso(@RequestBody @Valid DatosCrearCurso datos, UriComponentsBuilder uriComponentsBuilder){
        var curso = new Curso(datos);
        repository.save(curso);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(curso.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }
    @GetMapping
    public ResponseEntity<Page<DatosDetalleCurso>> obtenerCursos(@PageableDefault(size = 10) Pageable paginacion){
        var page = repository.findAll(paginacion).map(DatosDetalleCurso::new);
        return ResponseEntity.ok(page);
    }
}
