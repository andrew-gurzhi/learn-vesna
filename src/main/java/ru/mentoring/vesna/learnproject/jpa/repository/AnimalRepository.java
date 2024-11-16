package ru.mentoring.vesna.learnproject.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mentoring.vesna.learnproject.jpa.entity.AnimalEntity;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
}