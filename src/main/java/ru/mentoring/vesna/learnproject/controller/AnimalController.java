package ru.mentoring.vesna.learnproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mentoring.vesna.learnproject.service.AnimalService;
import ru.mentoring.vesna.learnproject.jpa.entity.AnimalEntity;

import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalEntity> createAnimal(@RequestBody AnimalEntity animalEntity) {
        AnimalEntity createdAnimal = animalService.createAnimal(animalEntity);
        return ResponseEntity.ok(createdAnimal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalEntity> getAnimalById(@PathVariable Long id) {
        Optional<AnimalEntity> animalEntity = animalService.getAnimalById(id);
        return animalEntity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}