package com.alura.forohub.domain.curso;

import com.alura.forohub.domain.topico.Topico;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosCrearCurso(
       @NotNull String nombre,
       @NotNull Categoria categoria
) {
}
