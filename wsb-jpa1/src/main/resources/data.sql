-- Insert into ADDRESS table
INSERT INTO ADDRESS (city, addressLine1, addressLine2, postalCode)
VALUES
    ('Warsaw', 'Nowa 1', 'Mieszkanie 10', '00-001'),
    ('Krakow', 'Stara 5', 'Mieszkanie 20', '31-010'),
    ('Gdansk', 'Morska 10', NULL, '80-100');

-- Insert into DOCTOR table
INSERT INTO DOCTOR (firstName, lastName, telephoneNumber, email, doctorNumber, specialization, address_id)
VALUES
    ('Jan', 'Kowalski', '123456789', 'jan.kowalski@email.com', 'D12345', 'CARDIOLOGY', 1),
    ('Anna', 'Nowak', '987654321', 'anna.nowak@email.com', 'D54321', 'PEDIATRICS', 2);

-- Insert into PATIENT table
INSERT INTO PATIENT (firstName, lastName, telephoneNumber, email, patientNumber, dateOfBirth, address_id)
VALUES
    ('Piotr', 'Zalewski', '555555555', 'piotr.zalewski@email.com', 'P1001', '1980-01-01', 1),
    ('Maria', 'Zielinska', '666666666', 'maria.zielinska@email.com', 'P1002', '1990-05-15', 2),
    ('Katarzyna', 'Kowalczyk', '777777777', 'katarzyna.kowalczyk@email.com', 'P1003', '2000-03-10', 3);

-- Insert into MEDICAL_TREATMENT table
INSERT INTO MEDICAL_TREATMENT (description, type)
VALUES
    ('Cardiac consultation', 'EXAMINATION'),
    ('Pediatric check-up', 'EXAMINATION'),
    ('Vaccination', 'PROCEDURE');

-- Insert into VISIT table
INSERT INTO VISIT (description, time, doctor_id, patient_id, treatment_id)
VALUES
    ('Cardiac examination and consultation', '2024-11-30T10:00:00', 1, 1, 1),
    ('Pediatric consultation', '2024-11-30T11:00:00', 2, 2, 2),
    ('Vaccination for children', '2024-11-30T12:00:00', 2, 3, 3);
