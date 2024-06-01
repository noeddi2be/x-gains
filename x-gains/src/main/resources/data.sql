-- Insert Users
INSERT INTO account (user_id, account_type, username, email, password, firstname, lastname, birthdate)
VALUES 
    (9999, 'user', 'Emy', 'emily.sharp@gmail.com', '$2a$10$KkQtRnijBLY/HOX2IvFlwOllFfxfs8gRQJ1AQzRtXRqADXR2TKmWO', 'Emily', 'Sharp', '1979-10-12'),
    (9998, 'user', 'Pete', 'pete.david@home.com', '$2a$10$KkQtRnijBLY/HOX2IvFlwOllFfxfs8gRQJ1AQzRtXRqADXR2TKmWO', 'Pete', 'Davidson', '1984-03-01'),
    (9997, 'user', 'GigaChad50', 'gg5@icloud.com', '$2a$10$KkQtRnijBLY/HOX2IvFlwOllFfxfs8gRQJ1AQzRtXRqADXR2TKmWO', 'Bill', 'Gates', '1960-05-02');

-- Insert Locations
INSERT INTO location (location_id, location_name) VALUES (20, 'Brugg'), (19, 'Basel'), (18, 'Olten');

-- Insert Workouts
INSERT INTO workout (workout_id, workout_name, workout_date, duration, fk_user_id, fk_location_id)
VALUES 
    (9999, 'Morning Workout', '2024-04-01', 60, 9999, 20), -- User 1 in Brugg
    (9998, 'Afternoon Workout', '2024-04-01', 60, 9998, 19), -- User 2 in Basel
    (9997, 'Evening Workout', '2024-04-01', 60, 9997, 18), -- User 3 in Olten
    (9996, 'Morning Workout', '2024-04-08', 60, 9999, 20), -- User 1 in Brugg
    (9995, 'Afternoon Workout', '2024-04-08', 60, 9998, 19), -- User 2 in Basel
    (9994, 'Evening Workout', '2024-04-08', 60, 9997, 18); -- User 3 in Olten

-- Insert Exercises
INSERT INTO exercise (exercise_id, exercise_name, exercise_description, weight, repetition, number_of_sets, time, distance, fk_workout_id)
VALUES 
    (9999, 'Bench Press', 'Chest exercise using a barbell', 50, 10, 3, NULL, NULL, 9999),
    (9998, 'Burpees', 'Full-body exercise', NULL, 20, 3, NULL, NULL, 9999),
    (9997, 'Rowing', 'Cardio exercise using a rowing machine', NULL, NULL, NULL, 20, 500, 9999),
    (9996, 'Mountain Climbers', 'Full-body exercise', NULL, 30, 3, NULL, NULL, 9998),
    (9995, 'Pull Ups', 'Upper body exercise using a pull-up bar', 0, 10, 3, NULL, NULL, 9998),
    (9994, 'Push Ups', 'Upper body exercise using body weight', 0, 15, 3, NULL, NULL, 9998),
    (9993, 'Skull Crushers', 'Triceps exercise using dumbbells or a barbell', 20, 12, 3, NULL, NULL, 9998),
    (9992, 'Swimming', 'Full-body cardio exercise in water', NULL, NULL, NULL, 30, 500, 9997),
    (9991, 'Dead Lift', 'Compound exercise targeting multiple muscle groups', 70, 8, 3, NULL, NULL, 9996),
    (9989, 'Skip Rope', 'Cardio exercise using a jump rope', NULL, NULL, 3, 180, NULL, 9996),
    (9988, 'Arnold Press', 'Shoulder exercise using dumbbells', 15, 10, 3, NULL, NULL, 9996),
    (9987, 'Biceps Curl', 'Biceps exercise using dumbbells or a barbell', 10, 12, 3, NULL, NULL, 9995),
    (9986, 'Thrusters', 'Full-body exercise combining a squat and overhead press', 25, 8, 3, NULL, NULL, 9995),
    (9985, 'Chest Flys', 'Chest exercise using dumbbells or a cable machine', 20, 12, 3, NULL, NULL, 9995),
    (9984, 'Tricep Extension', 'Triceps exercise using a dumbbell or cable machine', 15, 12, 3, NULL, NULL, 9995),
    (9983, 'Lat Pull', 'Back exercise using a lat pulldown machine', 40, 10, 3, NULL, NULL, 9998),
    (9982, 'Running', 'Cardio exercise outdoors or on a treadmill', NULL, NULL, NULL, 30, 500, 9994),
    (9981, 'Sprints', 'High-intensity interval running', NULL, NULL, 3, 60, 100, 9994),
    (9979, 'Box Jumps', 'Plyometric exercise using a box or platform', 0, 10, 3, NULL, NULL, 9999);

-- Assign Exercises to Workouts
/* INSERT INTO workouts_exercises (fk_workout_id, fk_exercise_id)
VALUES 
    (9999, 9999), (9999, 9998), (9999, 9997), -- User 1's Morning Workout in Brugg
    (9998, 9996), (9998, 9995), (9998, 9994), -- User 2's Afternoon Workout in Basel
    (9997, 9993), (9997, 9992), (9997, 9991); -- User 3's Evening Workout in Olten */
