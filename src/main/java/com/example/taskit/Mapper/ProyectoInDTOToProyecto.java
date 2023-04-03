package com.example.taskit.Mapper;

import com.example.taskit.Persistence.Entity.Proyecto;
import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Service.DTO.ProyectoInDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProyectoInDTOToProyecto implements IMapper<ProyectoInDTO, Proyecto>{
    @Override
    public Proyecto map(ProyectoInDTO in) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(in.getNombre());
        proyecto.setLista_tareas(new ArrayList<Tarea>());
        return proyecto;
    }
}
