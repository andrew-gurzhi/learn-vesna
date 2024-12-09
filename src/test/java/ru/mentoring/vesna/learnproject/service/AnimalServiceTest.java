package ru.mentoring.vesna.learnproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.mentoring.vesna.learnproject.jpa.entity.AnimalEntity;
import ru.mentoring.vesna.learnproject.jpa.repository.AnimalRepository;
import ru.mentoring.vesna.learnproject.model.Animal;
import ru.mentoring.vesna.learnproject.model.enumerated.AnimalType;
import ru.mentoring.vesna.learnproject.model.enumerated.Gender;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAnimal() {

        Animal animal = new Animal(1L, "Simba", AnimalType.CAT, 120, 5, Gender.MALE);
        AnimalEntity savedEntity = new AnimalEntity(1L, "Simba", ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.CAT, 120, 5, ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.MALE);

        when(animalRepository.save(any(AnimalEntity.class))).thenReturn(savedEntity);

        Animal createdAnimal = animalService.createAnimal(animal);

        assertNotNull(createdAnimal);
        assertEquals(1L, createdAnimal.getId());
        assertEquals("Simba", createdAnimal.getName());
        assertEquals(AnimalType.CAT, createdAnimal.getType());
        assertEquals(120, createdAnimal.getHeight());
        assertEquals(5, createdAnimal.getAge());
        assertEquals(Gender.MALE, createdAnimal.getGender());
        verify(animalRepository, times(1)).save(any(AnimalEntity.class));
    }

    @Test
    void testGetAnimalById() {

        AnimalEntity foundEntity = new AnimalEntity(1L, "Luntic", ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.OTHER, 110, 123, ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.UNKNOWN);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(foundEntity));

        Animal foundAnimal = animalService.getAnimalById(1L);

        assertNotNull(foundAnimal);
        assertEquals(1L, foundAnimal.getId());
        assertEquals("Luntic", foundAnimal.getName());
        assertEquals(AnimalType.OTHER, foundAnimal.getType());
        assertEquals(110, foundAnimal.getHeight());
        assertEquals(123, foundAnimal.getAge());
        assertEquals(Gender.UNKNOWN, foundAnimal.getGender());
        verify(animalRepository, times(1)).findById(1L);
    }

    @Test
    void testFailureGetAnimalById() {

        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> animalService.getAnimalById(1L));
        verify(animalRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAnimalsByName() {

        AnimalEntity animal1 = new AnimalEntity(1L, "Bruno", ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.DOG, 30, 1, ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.MALE);
        AnimalEntity animal2 = new AnimalEntity(2L, "Bruno", ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.DOG, 60, 15, ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.MALE);
        when(animalRepository.findByName("Bruno")).thenReturn(List.of(animal1, animal2));

        List<Animal> animals = animalService.getAnimalsByName("Bruno");

        assertNotNull(animals);
        assertEquals(2, animals.size());
        assertEquals("Bruno", animals.get(0).getName());
        assertEquals(1, animals.get(0).getAge());
        assertEquals(15, animals.get(1).getAge());
        verify(animalRepository, times(1)).findByName("Bruno");
    }

    @Test
    void testGetAllAnimals() {

        int limit = 2;
        int offset = 0;

        AnimalEntity entity1 = new AnimalEntity(1L, "Lion", ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.CAT, 30, 5, ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.MALE);
        AnimalEntity entity2 = new AnimalEntity(2L, "Tiger", ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.CAT, 130, 4, ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.FEMALE);

        List<AnimalEntity> entities = List.of(entity1, entity2);
        Page<AnimalEntity> page = new PageImpl<>(entities, PageRequest.of(offset, limit), entities.size());

        when(animalRepository.findAll(PageRequest.of(offset, limit))).thenReturn(page);

        Page<Animal> result = animalService.getAllAnimals(limit, offset);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());

        Animal animal1 = result.getContent().get(0);
        assertEquals("Lion", animal1.getName());
        assertEquals(AnimalType.CAT, animal1.getType());

        Animal animal2 = result.getContent().get(1);
        assertEquals("Tiger", animal2.getName());
        assertEquals(AnimalType.CAT, animal2.getType());

        verify(animalRepository, times(1)).findAll(PageRequest.of(offset, limit));
    }
}