package com.example.taskit.Service;

import com.example.taskit.Mapper.ProyectoInDTOToProyecto;
import com.example.taskit.Mapper.TareaInDTOToTarea;
import com.example.taskit.Persistence.Entity.Proyecto;
import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Persistence.Repository.ProyectoRepository;
import com.example.taskit.Persistence.Repository.TareaRepository;
import com.example.taskit.Service.DTO.ProyectoInDTO;
import com.example.taskit.Service.DTO.TareaInDTO;

public class ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final TareaRepository tareaRepository;
    private final ProyectoInDTOToProyecto proyectoMapper;
    private final TareaInDTOToTarea tareaMapper;

    public ProyectoService(ProyectoRepository repository, TareaRepository tareaRepository, ProyectoInDTOToProyecto mapper, TareaInDTOToTarea tareaMapper) {
        this.proyectoRepository = repository;
        this.tareaRepository = tareaRepository;
        this.proyectoMapper = mapper;
        this.tareaMapper = tareaMapper;
    }

    public Proyecto crearProyecto(ProyectoInDTO proyectoInDTO){
        Proyecto proyecto = proyectoMapper.map(proyectoInDTO);
        return this.proyectoRepository.save(proyecto);
    }

    public Proyecto agregarTarea(Long id_proyecto, TareaInDTO tareaInDTO){
        Tarea tarea = tareaMapper.map(tareaInDTO);
        Proyecto proyecto = proyectoRepository.getReferenceById(id_proyecto);
        proyecto.getLista_tareas().add(tarea);
        return proyecto;
    }
}
