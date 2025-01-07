
-- Insert into ADDRESS table

INSERT INTO ADDRESS (city, address_Line1, address_Line2, postal_Code)
VALUES
    ('Warsaw', 'Nowa 1', 'Mieszkanie', '00-001'),
    ('Krakow', 'Stara 5', 'Mieszkanie', '31-010'),
    ('Gdansk', 'Morska 10', 'TEST', '80-100');

-- Insert into DOCTOR table
INSERT INTO DOCTOR (first_Name, last_Name, telephone_Number, email, doctor_Number, specialization, address_id)
VALUES
    ('Jan', 'Kowalski', '123456789', 'jan.kowalski@email.com', 'D12345', 'OCULIST', 1),
    ('Anna', 'Nowak', '987654321', 'anna.nowak@email.com', 'D54321', 'OCULIST', 2);

-- Insert into PATIENT table
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES
    (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@example.com', 'P001', '1980-05-20', 43),
    (2, 'Anna', 'Nowak', '987654321', 'anna.nowak@example.com', 'P002', '1992-11-15', 31);

-- Insert into MEDICAL_TREATMENT table
INSERT INTO MEDICAL_TREATMENT (description, type)
VALUES
    ('Cardiac consultation', 'EXAMINATION'),
    ('Pediatric check-up', 'EXAMINATION'),
    ('Vaccination', 'PROCEDURE');


-- Dodanie wizyt
INSERT INTO VISIT (id, visit_date, description, patient_id, DOCTOR_ID)
VALUES
    (1, '2023-12-15 10:00:00', 'test', 1,1),
    (2, '2023-12-20 14:00:00', 'test2', 1,1),
    (3, '2023-12-15 14:20:00', 'test3 ', 1,2),
    (4, '2023-12-17 14:20:00', 'test4', 1,1);

-- Powiązanie wizyt z zabiegami (relacja wiele-do-wielu)
INSERT INTO VISIT_TREATMENT (visit_id, treatment_id)
VALUES
    (1, 1), -- Wizyta 1, zabieg 1
    (2, 2); -- Wizyta 2, zabieg 2

