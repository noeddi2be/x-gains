CREATE TABLE IF NOT EXISTS account
(
    user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    account_type VARCHAR(10) NOT NULL,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    salt INTEGER,
    hashed_password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    birthdate DATE
);

CREATE TABLE IF NOT EXISTS location 
(
    location_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    location_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS workout 
(
    workout_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    workout_name VARCHAR(50) NOT NULL,
    workout_date DATE NOT NULL,
    duration INTEGER NOT NULL,
    fk_user_id INTEGER NOT NULL,
    fk_location_id INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS exercise 
(
    exercise_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    exercise_name VARCHAR(50) NOT NULL,
    exercise_description VARCHAR(255) NOT NULL,
    weight INTEGER,
    repetition INTEGER,
    number_of_sets INTEGER,
    time INTEGER,
    distance INTEGER
);

CREATE TABLE IF NOT EXISTS including
(
    fk_workout_id INTEGER,
    fk_exercise_id INTEGER,
    FOREIGN KEY (fk_workout_id) REFERENCES workout (workout_id),
    FOREIGN KEY (fk_exercise_id) REFERENCES exercise (exercise_id),
    PRIMARY KEY (fk_workout_id, fk_exercise_id)
);

ALTER TABLE
workout
ADD FOREIGN KEY
(fk_location_id)
REFERENCES
location (location_id);

ALTER TABLE
workout
ADD FOREIGN KEY
(fk_user_id)
REFERENCES
account (user_id);