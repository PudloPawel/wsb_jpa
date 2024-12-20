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

-- Dodanie pacjentów
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES
    (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@example.com', 'P001', '1980-05-20', 43),
    (2, 'Anna', 'Nowak', '987654321', 'anna.nowak@example.com', 'P002', '1992-11-15', 31);

-- Dodanie lekarzy (możemy przechowywać ich w encji lub na poziomie wizyty)
-- Przykładowa tabela DOCTOR
INSERT INTO DOCTOR (id, first_name, last_name, specialization)
VALUES
    (1, 'Andrzej', 'Nowicki', 'Kardiologia'),
    (2, 'Maria', 'Kozłowska', 'Dermatologia');

-- Dodanie typów zabiegów
-- Przykładowa tabela MEDICAL_TREATMENT
INSERT INTO MEDICAL_TREATMENT (id, treatment_name, description)
VALUES
    (1, 'Badanie serca', 'Echokardiogram serca'),
    (2, 'Usuwanie pieprzyków', 'Zabieg dermatologiczny z wykorzystaniem lasera');

-- Dodanie wizyt
INSERT INTO VISIT (id, visit_date, doctor_name, patient_id)
VALUES
    (1, '2023-12-15 10:00:00', 'Dr. Andrzej Nowicki', 1),
    (2, '2023-12-20 14:00:00', 'Dr. Maria Kozłowska', 2);

-- Powiązanie wizyt z zabiegami (relacja wiele-do-wielu)
INSERT INTO VISIT_TREATMENT (visit_id, treatment_id)
VALUES
    (1, 1), -- Wizyta 1, zabieg 1
    (2, 2); -- Wizyta 2, zabieg 2
