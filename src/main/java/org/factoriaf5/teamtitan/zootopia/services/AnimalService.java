package org.factoriaf5.teamtitan.zootopia.services;

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
}
