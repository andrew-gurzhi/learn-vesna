package ru.mentoring.vesna.learnproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mentoring.vesna.learnproject.model.Animal;
import ru.mentoring.vesna.learnproject.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal( @RequestBody Animal animal ) {
        Animal createdAnimal = animalService.createAnimal( animal );
        return ResponseEntity.ok( createdAnimal );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById( @PathVariable Long id ) {
        Animal animal = animalService.getAnimalById( id );
        return ResponseEntity.ok( animal );
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Animal>> getAnimalByName(@PathVariable String name) {
        List<Animal> animalEntities = animalService.getAnimalsByName(name);
        if (animalEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animalEntities);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals(@RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "20") int limit) {
        Page<Animal> animalEntities = animalService.getAllAnimals(offset, limit);
        return ResponseEntity.ok(animalEntities.getContent());
    }
}