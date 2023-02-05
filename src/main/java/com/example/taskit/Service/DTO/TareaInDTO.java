package com.example.taskit.Service.DTO;

import com.example.taskit.Persistence.Entity.LocalDateAttributeConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TareaInDTO {
    private String titulo;
    private String descripcion;
    private LocalDate fecha_limite;
}
