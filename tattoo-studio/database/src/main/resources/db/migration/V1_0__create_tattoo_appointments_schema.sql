CREATE TABLE tattoo_appointments (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    tattoo_type VARCHAR(255) NOT NULL,
    appointment_time TIMESTAMP NOT NULL
);