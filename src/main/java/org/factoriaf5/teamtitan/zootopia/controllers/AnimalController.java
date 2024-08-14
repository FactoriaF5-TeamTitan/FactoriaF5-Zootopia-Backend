package org.factoriaf5.teamtitan.zootopia.controllers;

import java.util.List;

import org.factoriaf5.teamtitan.zootopia.models.Animal;
import org.factoriaf5.teamtitan.zootopia.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @PostMapping("/animal")
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal newAnimal = animalService.save(animal);
        return new ResponseEntity<>(newAnimal, HttpStatus.OK);

    }

    @GetMapping("/animals")
    public List<Animal> fetchAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PutMapping("/animal/{id}")
    public ResponseEntity<Animal> editAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.edit(id, animal);
        return new ResponseEntity<>(updatedAnimal, HttpStatus.OK);
    }

    @DeleteMapping("/animal/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/animals/size")
    public Integer getDataDashboard() {
        return animalService.getAllAnimals().size();
    }

}
