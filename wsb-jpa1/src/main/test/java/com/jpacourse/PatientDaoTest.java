package com.jpacourse;

import java.time.LocalDateTime;
import java.util.List;

import com.jpacourse.persistence.dao.impl.PatientDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PatientDaoTest {

    @Autowired
    private PatientDaoImpl patientDao;


    /**
     * Test dodawania nowej wizyty do pacjenta.
     */
    @Test
    public void testAddVisit() {
        // Przygotowanie danych
        Long patientId = 1L; // Założenie: PatientEntity o ID=1 istnieje w data.sql
        String doctorName = "Dr. Andrzej Nowicki";
        LocalDateTime visitDate = LocalDateTime.of(2023,12,15,10,0);

        // Dodanie wizyty
        patientDao.addVisit(patientId, doctorName, visitDate, "Kontrola kardiologiczna");

        // Pobranie pacjenta i weryfikacja wizyty
        PatientEntity patient = patientDao.findOne(patientId);
        Assertions.assertNotNull(patient, "Pacjent powinien istnieć.");
        List<VisitEntity> visits = patient.getVisits();
        Assertions.assertEquals(3, visits.size(), "Pacjent powinien mieć jedną wizytyę.");
        VisitEntity visit = visits.get(0);
        Assertions.assertEquals(doctorName, visit.getDoctorName());
        Assertions.assertEquals(visitDate, visit.getVisitDate());
    }



    /**
     * Test weryfikacji poprawności danych pacjenta, w tym dodatkowego pola `age`.
     */
    @Test
    @Transactional
    public void testRetrievePatientById() {
        // Przygotowanie danych
        Long patientId = 1L; // Założenie: PatientEntity o ID=1 istnieje w data.sql

        // Pobranie pacjenta
        PatientEntity patient = patientDao.findOne(patientId);
        Assertions.assertNotNull(patient, "Pacjent powinien istnieć.");
        Assertions.assertEquals("Jan", patient.getFirstName(), "Imię pacjenta powinno być 'Jan'.");
        Assertions.assertEquals(43, patient.getAge(), "Wiek pacjenta powinien wynosić 43.");
        Assertions.assertNotNull(patient.getVisits(), "Lista wizyt powinna być zainicjowana.");
    }
}
