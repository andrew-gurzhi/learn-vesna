package ru.mentoring.vesna.learnproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mentoring.vesna.learnproject.jpa.entity.AnimalEntity;
import ru.mentoring.vesna.learnproject.jpa.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;


    public AnimalEntity createAnimal(AnimalEntity animalEntity) {
        return animalRepository.save(animalEntity);
    }

    public Optional<AnimalEntity> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public List<AnimalEntity> getAnimalsByName(String name) { return animalRepository.findByName(name);
    }
}