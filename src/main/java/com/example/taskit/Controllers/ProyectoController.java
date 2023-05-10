package com.example.taskit.Controllers;

import com.example.taskit.Persistence.Entity.Proyecto;
import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Service.DTO.ProyectoInDTO;
import com.example.taskit.Service.DTO.TareaInDTO;
import com.example.taskit.Service.ProyectoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @PostMapping("/crear")
    public Proyecto crearProyecto(@RequestBody ProyectoInDTO proyectoInDTO){
        return this.proyectoService.crearProyecto(proyectoInDTO);
    }

    @PostMapping("/agregar_tarea")
    public Tarea agregarTarea(@RequestBody TareaInDTO tareaInDTO){
        return this.proyectoService.agregarTarea(1L, tareaInDTO);
    }
}
