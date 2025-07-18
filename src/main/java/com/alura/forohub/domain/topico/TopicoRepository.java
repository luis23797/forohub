package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Page<Topico> findAllByStatusTrue(Pageable paginacion);


    Optional<Topico> findByTitulo( String titulo);

    Optional<Topico> findByMensaje(String mensaje);
}
