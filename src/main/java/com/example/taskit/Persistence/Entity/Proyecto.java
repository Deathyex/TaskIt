package com.example.taskit.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tarea> lista_tareas;
}
