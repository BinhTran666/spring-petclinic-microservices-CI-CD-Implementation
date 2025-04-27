package org.springframework.samples.petclinic.vets.web;

import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.Specialty;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class VetTest {

    @Test
    void shouldManageSpecialties() {
        // Arrange
        Vet vet = new Vet();
        Specialty radiology = new Specialty();
        radiology.setName("radiology");
        
        // Act
        vet.addSpecialty(radiology);
        
        // Assert
        assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
        assertThat(vet.getSpecialties().get(0).getName()).isEqualTo("radiology");
    }

    @Test
    void shouldHandlePersonProperties() {
        Vet vet = new Vet();
        vet.setFirstName("James");
        vet.setLastName("Carter");
        
        assertThat(vet.getFirstName()).isEqualTo("James");
        assertThat(vet.getLastName()).isEqualTo("Carter");
    }
}