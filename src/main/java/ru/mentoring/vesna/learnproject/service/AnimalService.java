package ru.mentoring.vesna.learnproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mentoring.vesna.learnproject.jpa.repository.AnimalRepository;
import ru.mentoring.vesna.learnproject.model.Animal;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;


    public Animal createAnimal( Animal animal ) {
        return Animal.fromEntity( animalRepository.save( animal.toEntity() ) );
    }

    public Animal getAnimalById( Long id ) {
        return Animal.fromEntity( animalRepository.findById( id ).orElseThrow() );
    }
}