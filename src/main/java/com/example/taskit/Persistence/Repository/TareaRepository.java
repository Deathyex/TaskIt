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
    List<Tarea> findAllByEstadoIs(Tarea.Estado estado);
    List<Tarea> findAllByFechaLimiteBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Tarea> findAllByFechaLimiteBetweenAndTituloLikeIgnoreCase(LocalDate fechaInicio, LocalDate fechaFin, String titulo);
    List<Tarea> findAllByTituloLikeIgnoreCaseOrDescripcionLikeIgnoreCase(String texto1, String texto2);

    @Modifying
    @Query(value = "UPDATE tarea SET estado=1, fecha_finalizacion = CURRENT_TIMESTAMP WHERE tarea.id = :id", nativeQuery = true)
    public void finalizarTarea(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE tarea SET estado=3, fecha_finalizacion = CURRENT_TIMESTAMP WHERE tarea.id = :id", nativeQuery = true)
    public void inactivarTarea(@Param("id") Long id);
}

