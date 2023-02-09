package com.example.taskit.Controllers;

import com.example.taskit.Persistence.Entity.Tarea;
import com.example.taskit.Service.DTO.TareaInDTO;
import com.example.taskit.Service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/listar/{id}")
    public Tarea findTarea(@PathVariable("id") Long id){
        return this.tareaService.findTarea(id);
    }

    @GetMapping("/listar")
    public List<Tarea> findAll(){
        return this.tareaService.findAll();
    }

    @GetMapping("/listar/pendientes")
    public List<Tarea> findAllPendientes(){
        return this.tareaService.findAllPendientes();
    }

    @GetMapping("/listar/finalizadas")
    public List<Tarea> findAllFinalizadas(){
        return this.tareaService.findAllFinalizadas();
    }

    @GetMapping("/listar/filtrar")
    public List<Tarea> filtrar(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){
        return this.tareaService.filtrar(fechaInicio, fechaFin);
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

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> inactivarTarea(@PathVariable("id") Long id){
        this.tareaService.inactivarTarea(id);
        return ResponseEntity.noContent().build();
    }
}

