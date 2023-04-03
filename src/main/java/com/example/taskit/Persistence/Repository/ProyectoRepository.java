package com.example.taskit.Persistence.Repository;

import com.example.taskit.Persistence.Entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
