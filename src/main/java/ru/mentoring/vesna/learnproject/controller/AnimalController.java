package ru.mentoring.vesna.learnproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mentoring.vesna.learnproject.model.Animal;
import ru.mentoring.vesna.learnproject.service.AnimalService;
import ru.mentoring.vesna.learnproject.jpa.entity.AnimalEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController( AnimalService animalService ) {
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
    public ResponseEntity<List<AnimalEntity>> getAnimalByName(@PathVariable String name) {
        List<AnimalEntity> animalEntities = animalService.getAnimalsByName(name);
        if (animalEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animalEntities);
    }
}