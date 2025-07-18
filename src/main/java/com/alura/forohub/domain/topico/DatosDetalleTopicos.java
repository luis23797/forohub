package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopicos(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fecha

) {
    public DatosDetalleTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion()
        );
    }
}
