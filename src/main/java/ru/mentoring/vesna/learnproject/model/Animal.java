package ru.mentoring.vesna.learnproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mentoring.vesna.learnproject.jpa.entity.AnimalEntity;
import ru.mentoring.vesna.learnproject.model.enumerated.AnimalType;
import ru.mentoring.vesna.learnproject.model.enumerated.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private Long id;
    private String name;
    private AnimalType type;
    private Integer height;
    private Integer age;
    private Gender gender;

    public static Animal fromEntity( AnimalEntity entity ) {
        if ( entity == null ) {
            return null;
        }
        return new Animal(
                entity.getId(),
                entity.getName(),
                entity.getType() != null ? AnimalType.valueOf( entity.getType().name() ) : null,
                entity.getHeight(),
                entity.getAge(),
                entity.getGender() != null ? Gender.valueOf( entity.getGender().name() ) : null
        );
    }

    public AnimalEntity toEntity() {
        AnimalEntity entity = new AnimalEntity();
        entity.setId( this.id );
        entity.setName( this.name );
        entity.setType( this.type != null ? ru.mentoring.vesna.learnproject.jpa.entity.enumerated.AnimalType.valueOf( this.type.name() ) : null );
        entity.setHeight( this.height );
        entity.setAge( this.age );
        entity.setGender( this.gender != null ? ru.mentoring.vesna.learnproject.jpa.entity.enumerated.Gender.valueOf( this.gender.name() ) : null );
        return entity;
    }
}