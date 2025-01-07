package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void addVisit(LocalDateTime visitDate, String treatmentDescription,Long patientId, DoctorEntity doctor) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found with ID: " + patientId);
        }

        VisitEntity visit = new VisitEntity();
        visit.setDoctor(doctor);
        visit.setDescription(treatmentDescription);
        visit.setVisitDate(visitDate);
        visit.setPatient(patient);
        // Dodanie prostego opisu wizyty (rozbudowa możliwa w encji Visit)
        // Można dodać logikę tworzenia treatmentów i dodawania ich do wizyty

        patient.getVisits().add(visit);
        entityManager.merge(patient);
    }

    @Override
    public List<DoctorEntity> findByVisitDescription(String pDesc) {
       return entityManager.createQuery(" select doc from DoctorEntity doc " +
               " join doc.visits visit " +
                    " where visit.description = :desc", DoctorEntity.class)
               .setParameter("desc", pDesc)
               .getResultList();
    }

    @Override
    public List<VisitEntity> findVisitByIdPatient(Long id) {
        return entityManager.createQuery("select visit from VisitEntity visit " +
                "join visit.patient patient " +
                    "where patient.id = :idPatient", VisitEntity.class)
                .setParameter("idPatient",id)
                .getResultList();
    }

    public List<PatientEntity> findByMinNumberOfVisits(@Param("minVisits") Integer minVisits) {
        return entityManager.createQuery(
                "select patient from PatientEntity patient " +
                        "where size(patient.visits) > :minNumberOfVisit", PatientEntity.class)
                .setParameter("minNumberOfVisit", minVisits)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByContainsLetterInTheName(String letter) {
        return entityManager.createQuery(
                "select patient from PatientEntity patient " +
                        "where patient.lastName like :pattern", PatientEntity.class)
                .setParameter("pattern", "%" + letter + "%")
                .getResultList();
    }


}
