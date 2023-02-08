package com.example.taskit.Mapper;

import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Service.DTO.TareaInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TareaInDTOToTarea implements IMapper<TareaInDTO, Tarea>{
    @Override
    public Tarea map(TareaInDTO in) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(in.getTitulo());
        tarea.setDescripcion(in.getDescripcion());
        tarea.setFechaLimite(in.getFechaLimite());
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setEstado(Tarea.Estado.PENDIENTE);
        return tarea;
    }
}
