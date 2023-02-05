package com.example.taskit.Persistence.Repository;

import com.example.taskit.Persistence.Entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    public List<Tarea> findAllByEstadoIs(Tarea.Estado estado);

    @Modifying
    @Query(value = "UPDATE tarea SET estado=1, fecha_finalizacion = CURRENT_TIMESTAMP WHERE tarea.id = :id", nativeQuery = true)
    public void finalizarTarea(@Param("id") Long id);
}

