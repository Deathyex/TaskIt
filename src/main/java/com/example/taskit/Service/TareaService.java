package com.example.taskit.Service;

import com.example.taskit.Mapper.TareaInDTOToTarea;
import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Persistence.Repository.TareaRepository;
import com.example.taskit.Service.DTO.TareaInDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

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

    public List<Tarea>  findAll(){
        return this.repository.findAll();
    }

    public List<Tarea> findAllByEstadoIs(Tarea.Estado estado){
        return this.repository.findAllByEstadoIs(estado);
    }

    @Transactional
    public void finalizarTarea(Long id){
        this.repository.finalizarTarea(id);
    }
}
