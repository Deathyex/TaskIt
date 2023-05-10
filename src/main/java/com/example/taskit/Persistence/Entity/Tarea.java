package com.example.taskit.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tarea")
public class Tarea {
    public enum Estado{
        PENDIENTE,
        FINALIZADA,
        RETRASADA,
        INACTIVA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private LocalDate fechaCreacion;
    private LocalDate fechaFinalizacion;
    private Estado estado;
    @ManyToOne
    private Proyecto proyecto;
}
