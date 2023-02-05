package com.example.taskit.Persistence.Entity;

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
    //@Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha_limite;
    //@Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha_creacion;
    //@Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha_finalizacion;
    private Estado estado;
}
