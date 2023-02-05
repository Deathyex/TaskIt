package com.example.taskit.Controllers;

import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Service.DTO.TareaInDTO;
import com.example.taskit.Service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/crear")
    public Tarea crearTarea(@RequestBody TareaInDTO tareaInDTO){
        return this.tareaService.crearTarea(tareaInDTO);
    }

    @GetMapping("/listar")
    public List<Tarea> findAll(){
        return this.tareaService.findAll();
    }

    @GetMapping("/listar/estado/{estado}")
    public List<Tarea> findAllByEstadoIs(@PathVariable("estado") Tarea.Estado estado){
        return this.tareaService.findAllByEstadoIs(estado);
    }

    @PatchMapping("/finalizar/{id}")
    public ResponseEntity<Void> finalizarTarea(@PathVariable("id") Long id){
        this.tareaService.finalizarTarea(id);
        return ResponseEntity.noContent().build();
    }
}

