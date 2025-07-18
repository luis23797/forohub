package com.alura.forohub.domain.topico.validaciones.edicion;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.topico.DatosEditarTopico;
import com.alura.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorDeEdicionTopicoConTituloYMensajeIgual")
public class ValidadorTopicoIdentico implements ValidadorDeEdicionTopicos{

    @Autowired
    private TopicoRepository repository;


    @Override
    public void validar(DatosEditarTopico datos) {
        if(datos.titulo()!= null){
            var topico = repository.findByTitulo(datos.titulo());
            if(topico.isPresent()){
                throw new ValidacionException("El titulo del topico ya ha sido tomado");
            }
        }
        if(datos.mensaje()!= null){
            var topico = repository.findByMensaje(datos.mensaje());
            if(topico.isPresent()){
                throw new ValidacionException("El mensaje del topico ya ha sido tomado");
            }
        }
    }
}
