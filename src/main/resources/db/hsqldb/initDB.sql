DROP TABLE vet_specialties IF EXISTS;
DROP TABLE comments IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE statistics IF EXISTS;
DROP TABLE responses IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE services IF EXISTS;


CREATE TABLE comments (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON comments (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES comments (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE services (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  name    VARCHAR(255),
  description       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON services (last_name);

CREATE TABLE responses (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER NOT NULL
);
ALTER TABLE responses ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES services (id);
ALTER TABLE responses ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON responses (name);

CREATE TABLE statistics (
  id          INTEGER IDENTITY PRIMARY KEY,
  pet_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE statistics ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES responses (id);
CREATE INDEX visits_pet_id ON statistics (pet_id);
