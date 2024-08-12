package org.factoriaf5.teamtitan.zootopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;

import org.factoriaf5.teamtitan.zootopia.models.Animal;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testAnimal_withAllArgsConstructor_setsFieldsCorrectly() {
        String name = "Lion";
        String type = "Carnivore";
        String family = "Felidae";
        String genre = "Panthera";
        Date date = Date.valueOf("2023-01-01");
        String image = "lion.jpg";

        Animal animal = new Animal(name, type, family, genre, date, image);

        assertEquals(name, animal.getName());
        assertEquals(type, animal.getType());
        assertEquals(family, animal.getFamily());
        assertEquals(genre, animal.getGenre());
        assertEquals(date, animal.getDate());
        assertEquals(image, animal.getImage());
    }

    @Test
    public void testAnimal_withNoArgsConstructor_initializesEmptyObject() {
        Animal animal = new Animal();

        assertNull(animal.getName());
        assertNull(animal.getType());
        assertNull(animal.getFamily());
        assertNull(animal.getGenre());
        assertNull(animal.getDate());
        assertNull(animal.getImage());
    }

    @Test
    public void testSetterAndGetters_updateFieldsCorrectly() {
        Animal animal = new Animal();

        String newName = "Tiger";
        String newType = "Carnivore";

        animal.setName(newName);
        animal.setType(newType);

        assertEquals(newName, animal.getName());
        assertEquals(newType, animal.getType());
    }

}
