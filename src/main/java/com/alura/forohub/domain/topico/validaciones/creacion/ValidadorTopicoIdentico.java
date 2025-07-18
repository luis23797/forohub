package com.alura.forohub.domain.topico.validaciones.creacion;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.topico.DatosCrearTopico;
import com.alura.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorDeCreacionTopicoConTituloYMensajeIgual")
public class ValidadorTopicoIdentico implements ValidadorDeCreacionTopicos {
  @Autowired
  private TopicoRepository repository;
    public void validar(DatosCrearTopico datos) {
        var topico = repository.findByTitulo(datos.titulo());
        if(topico.isPresent()){
            throw new ValidacionException("El titulo del topico ya ha sido tomado");
        }
        topico = repository.findByMensaje(datos.mensaje());
        if(topico.isPresent()){
            throw new ValidacionException("El mensaje del topico ya ha sido tomado");
        }
    }
}
