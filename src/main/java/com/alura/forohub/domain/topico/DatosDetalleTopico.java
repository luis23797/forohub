package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.DatosDetalleCurso;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        Boolean activo,
        DatosDetalleCurso curso,
        LocalDateTime fecha

) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getStatus(),
                new DatosDetalleCurso(topico.getCurso()),
                topico.getFechaCreacion()
        );
    }
}
