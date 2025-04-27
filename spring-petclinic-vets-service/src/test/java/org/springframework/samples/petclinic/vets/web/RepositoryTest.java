package org.springframework.samples.petclinic.vets.web;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.samples.petclinic.vets.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VetRepositoryTest {

    @Autowired
    private VetRepository vetRepository;

    @Test
    void shouldFindAllVets() {
        Collection<Vet> vets = this.vetRepository.findAll();
        
        assertThat(vets).isNotEmpty();
        // Sample data should have at least one vet with specialties
        boolean hasVetWithSpecialty = vets.stream()
            .anyMatch(v -> v.getSpecialties() != null && !v.getSpecialties().isEmpty());
        assertThat(hasVetWithSpecialty).isTrue();
    }
    
    @Test
    @Transactional
    void shouldSaveVet() {
        int initialCount = vetRepository.findAll().size();
        
        Vet vet = new Vet();
        vet.setFirstName("John");
        vet.setLastName("Doe");
        
        Specialty surgery = new Specialty();
        surgery.setName("surgery");
        vet.addSpecialty(surgery);
        
        vetRepository.save(vet);
        
        assertThat(vet.getId()).isNotNull();
        assertThat(vetRepository.findAll().size()).isEqualTo(initialCount + 1);
    }
}