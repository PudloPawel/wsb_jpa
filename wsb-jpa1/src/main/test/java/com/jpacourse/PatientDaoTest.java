package com.jpacourse;

import java.time.LocalDateTime;
import java.util.List;

import com.jpacourse.persistence.dao.impl.PatientDaoImpl;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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


        Long patientId = 2L;
        LocalDateTime visitDate = LocalDateTime.of(2023,12,15,10,0);
        DoctorEntity doctor = new DoctorEntity(1L,"Jan", "Kowal", "123455689", "jan.kowalski22@email.com", "D11345", Specialization.DERMATOLOGIST);

        // Dodanie wizyty
        patientDao.addVisit(visitDate, "test3", patientId, doctor);
        // Pobranie pacjenta i weryfikacja wizyty
        PatientEntity patient = patientDao.findOne(patientId);
        Assertions.assertNotNull(patient, "Pacjent powinien istnieć.");
        List<VisitEntity> visits = patientDao.findVisitByIdPatient(patientId);
        System.out.println(patientId);
        Assertions.assertEquals(1, visits.size(), "Pacjent powinien mieć jedną wizyte.");
        VisitEntity visit = visits.get(0);
        Assertions.assertEquals("test3", visit.getDescription());
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

    // Testy Lab3

    @Test
    void testFindByVisitDescription() {


        List<DoctorEntity> doctors = patientDao.findByVisitDescription("test");

        assertThat(doctors).isNotNull();
        DoctorEntity doctor = doctors.get(0);
        assertThat(doctor.getId()).isEqualTo(1);
    }

    @Test
    void testFindVisitByIdPatient() {


        List<VisitEntity> visits = patientDao.findVisitByIdPatient(1L);

        assertThat(visits).isNotNull();
        VisitEntity visit = visits.get(0);
        assertThat(visit.getId()).isEqualTo(1);
    }

    @Test
    void testFindByMinNumberOfVisits() {


        List<PatientEntity> patients = patientDao.findByMinNumberOfVisits(Integer.valueOf(2));

        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(1);
    }

    @Test
    void testFindByContainsLetterInTheName() {


        List<PatientEntity> patients = patientDao.findByContainsLetterInTheName("l");

        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(1);
    }


}
