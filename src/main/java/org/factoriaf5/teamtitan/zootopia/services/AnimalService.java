package org.factoriaf5.teamtitan.zootopia.services;

import java.sql.Date;

import org.factoriaf5.teamtitan.zootopia.models.Animal;
import org.factoriaf5.teamtitan.zootopia.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public void delete(Animal animal) {
        animalRepository.delete(animal);
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow();
    }

    public Animal edit(Long id, Animal animal) {
        Animal updatedAnimal = animalRepository.findById(id).orElseThrow();
        updatedAnimal.setDate(animal.getDate());
        updatedAnimal.setFamily(animal.getFamily());
        updatedAnimal.setGenre(animal.getGenre());
        updatedAnimal.setName(animal.getName());
        updatedAnimal.setType(animal.getType());
        return updatedAnimal;
    }
}
