package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Estudiante {

    @Id
    private Long id;

    private @Getter String nombre;
    private @Getter String carrera;

    protected Estudiante() {

    }

    public Estudiante(Long id, String nombre, String carrera) {
        if (id == null || nombre == null) {
            throw new IllegalArgumentException("Datos inválidos");
        }
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
    }


}
