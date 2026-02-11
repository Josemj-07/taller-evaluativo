package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.RepositorioEstudiante;
import com.example.demo.model.Estudiante;

@RestController
class EstudianteController {
    private final RepositorioEstudiante repositorioEstudiante;

    EstudianteController(RepositorioEstudiante repositorioEstudiante) {
        this.repositorioEstudiante = repositorioEstudiante;
    }

    @GetMapping("/estudiantes")
    List<Estudiante> all() {
        return repositorioEstudiante.findAll();
    }

    @PostMapping("/estudiantes")
    ResponseEntity<String> recibirNuevoEstudiante(@RequestBody Estudiante nuevoEstudiante) {
        if(repositorioEstudiante.existsById(nuevoEstudiante.getId())) {
            throw new IllegalArgumentException("El estudiante ya existe");
        }
        repositorioEstudiante.save(nuevoEstudiante);
        return ResponseEntity.ok("El estudiante ha sido añadido existosamente");
    }
}
