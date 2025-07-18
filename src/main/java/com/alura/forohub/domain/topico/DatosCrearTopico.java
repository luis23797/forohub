package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(
        @NotNull String titulo,
        @NotNull String mensaje,
        @NotNull Long autor_id,
        @NotNull Long curso_id
) {

}
