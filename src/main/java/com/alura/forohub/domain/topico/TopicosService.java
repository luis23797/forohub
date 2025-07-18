package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.topico.validaciones.creacion.ValidadorDeCreacionTopicos;
import com.alura.forohub.domain.topico.validaciones.edicion.ValidadorDeEdicionTopicos;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicosService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorDeCreacionTopicos> validadoresCrearTopico;
    @Autowired
    private List<ValidadorDeEdicionTopicos> validadorDeEdicionTopico;

    public DatosDetalleTopicos crear(@Valid DatosCrearTopico datosTopico) {
        if(!usuarioRepository.existsById(datosTopico.autor_id())) throw new ValidacionException("No existe un usuario con el id proveido");
        if(!cursoRepository.existsById(datosTopico.curso_id())) throw new ValidacionException("No existe un curso con el id proveido");

        validadoresCrearTopico.forEach(validador->validador.validar(datosTopico));

        var usuario = usuarioRepository.getReferenceById(datosTopico.autor_id());
        var curso = cursoRepository.getReferenceById(datosTopico.curso_id());
        var topico = new Topico(null,datosTopico.titulo(),datosTopico.mensaje(), LocalDateTime.now(),true,usuario,curso);
        topicoRepository.save(topico);
        return new DatosDetalleTopicos(topico);
    }

    public Page<DatosDetalleTopicos> obtenerTopicos(Pageable paginacion) {

        return topicoRepository.findAllByStatusTrue(paginacion).map(DatosDetalleTopicos::new);
    }

    public DatosDetalleTopico obtenerTopico(Long id) {
        if(!topicoRepository.existsById(id)) throw new ValidacionException("No existe un topico con el id proveido");
        var topico = topicoRepository.getReferenceById(id);
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico editarTopico(Long id,DatosEditarTopico datos) {
        if(!topicoRepository.existsById(id)) throw new ValidacionException("No existe un topico con el id proveido");

        validadorDeEdicionTopico.forEach(validador->validador.validar(datos));

        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarInformacion(datos);
        return new DatosDetalleTopico(topico);
    }

    public void eliminarTopico(Long id) {
        if(!topicoRepository.existsById(id)) throw new ValidacionException("No existe un topico con el id proveido");
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
    }
}
