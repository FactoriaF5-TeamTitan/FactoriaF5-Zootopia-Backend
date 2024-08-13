package org.factoriaf5.teamtitan.zootopia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.factoriaf5.teamtitan.zootopia.controllers.AnimalController;
import org.factoriaf5.teamtitan.zootopia.models.Animal;
import org.factoriaf5.teamtitan.zootopia.services.AnimalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AnimalControllerTest {

    @Autowired
    private AnimalController animalController;

    @MockBean
    private AnimalService animalService;

    @Test
    public void testGetAnimalById_notFound() {
        Long id = 1L;
        Mockito.when(animalService.getAnimalById(id)).thenReturn(null);

        ResponseEntity<Animal> response = animalController.getAnimal(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
