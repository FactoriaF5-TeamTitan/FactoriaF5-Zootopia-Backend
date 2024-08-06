package org.factoriaf5.teamtitan.zootopia.controllers;

import org.factoriaf5.teamtitan.zootopia.models.Animal;
import org.factoriaf5.teamtitan.zootopia.services.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AnimalController {
    private AnimalService animalService;

    @PostMapping("/animal")
    public ResponseEntity<Animal> create(Animal animal) {
        Animal newAnimal = animalService.save(animal);
        return new ResponseEntity<>(newAnimal, HttpStatus.OK);

    }
}
