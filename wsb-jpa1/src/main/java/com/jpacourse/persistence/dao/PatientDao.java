package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;

@Repository
public interface PatientDao extends Dao<PatientEntity, Long>{

    List<DoctorEntity> findByVisitDescription(String pDesc);

    List<VisitEntity> findVisitByIdPatient(Long id);

    List<PatientEntity> findByMinNumberOfVisits(Integer minNumberOfVisits);

    List<PatientEntity> findByContainsLetterInTheName(String letter);

    /*
    List<DoctorEntity> findByVisitDescription(LocalDateTime pDateFrom, LocalDateTime pDateTo);

    List<DoctorEntity> findByPatient(String pPatientName);

    List<DoctorEntity> findByTreatmentType(TreatmentType treatmentType);

    List<DoctorEntity> findAllByJpql();

    long countVisitsOfDoctor(String pDocName, LocalDateTime pDateFrom, LocalDateTime pDateTo);

     */
}
