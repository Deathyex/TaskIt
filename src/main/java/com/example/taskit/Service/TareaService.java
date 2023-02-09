package com.example.taskit.Service;

import com.example.taskit.Mapper.TareaInDTOToTarea;
import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Persistence.Repository.TareaRepository;
import com.example.taskit.Service.DTO.TareaInDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TareaService {
    private final TareaRepository repository;
    private final TareaInDTOToTarea mapper;

    public TareaService(TareaRepository repository, TareaInDTOToTarea mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Tarea crearTarea(TareaInDTO tareaInDTO){
        Tarea tarea = mapper.map(tareaInDTO);
        return this.repository.save(tarea);
    }

    public Tarea findTarea(Long id){
        return this.repository.getReferenceById(id);
    }

    public List<Tarea>  findAll(){
        return this.repository.findAll();
    }

    public List<Tarea> findAllByEstadoIs(Tarea.Estado estado){
        return this.repository.findAllByEstadoIs(estado);
    }

    public List<Tarea> findAllPendientes(){
        return this.findAllByEstadoIs(Tarea.Estado.PENDIENTE);
    }

    public List<Tarea> findAllFinalizadas(){
        return this.findAllByEstadoIs(Tarea.Estado.FINALIZADA);
    }

    /*public List<Tarea> filtrar(LocalDate fechaInicio, LocalDate fechaFin, String texto){
        if(texto.isEmpty() && !(fechaInicio==null) && !(fechaFin==null)){
            return this.repository.findAllByFechaLimiteBetween(fechaInicio, fechaFin);
        }
        else if(!texto.isEmpty() && (fechaInicio==null) && (fechaFin==null)){
            return this.repository.findAllByTituloLikeIgnoreCaseOrDescripcionLikeIgnoreCase(texto, texto);
        }
        else if(!texto.isEmpty() && !(fechaInicio==null) && !(fechaFin==null)) {
            return this.repository.findAllByFechaLimiteBetweenAndTituloLikeIgnoreCase(fechaInicio, fechaFin, texto);
        } else {
            return this.findAllByEstadoIs(Tarea.Estado.PENDIENTE);
        }
    }*/

    public List<Tarea> filtrar(LocalDate fechaInicio, LocalDate fechaFin){
        return this.repository.findAllByFechaLimiteBetweenAndEstadoIs(fechaInicio, fechaFin, Tarea.Estado.PENDIENTE);
    }

    public List<Tarea> filtrarPorTituloDescripcion(String texto){
        return this.repository.findAllByTituloLikeIgnoreCaseOrDescripcionLikeIgnoreCase(texto, texto);
    }

    @Transactional
    public void finalizarTarea(Long id){
        this.repository.finalizarTarea(id);
    }

    @Transactional
    public void inactivarTarea(Long id){
        this.repository.inactivarTarea(id);
    }
}
