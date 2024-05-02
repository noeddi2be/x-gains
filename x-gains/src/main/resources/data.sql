-- Insert Users
INSERT INTO account (user_id, account_type, username, email, salt, hashed_password, firstname, lastname, birthdate)
VALUES 
    (9999, 'user', 'Emy', 'emily.sharp@gmail.com', 31296878, '3a9264c6611c2799199ee9012533fc80c94ce563c39460f9d9c0ac382a2a78b6906e9bee1ac55f242064d0d3f5405e4ca8245d67c65fe608291e196976fcdeb2', 'Emily', 'Sharp', '1979-10-12'),
    (9998, 'user', 'Pete', 'pete.david@home.com', 13058720, '3a9264c6611c2799199ee9012533fc80c94ce563c39460f9d9c0ac382a2a78b6906e9bee1ac55f242064d0d3f5405e4ca8245d67c65fe608291e196976fcdeb2', 'Pete', 'Davidson', '1984-03-01'),
    (9997, 'user', 'GigaChad50', 'gg5@icloud.com', 10238509, '3a9264c6611c2799199ee9012533fc80c94ce563c39460f9d9c0ac382a2a78b6906e9bee1ac55f242064d0d3f5405e4ca8245d67c65fe608291e196976fcdeb2', 'Bill', 'Gates', '1960-05-02');

-- Insert Locations
INSERT INTO location (location_id, location_name) VALUES (1, 'Brugg'), (2, 'Basel'), (3, 'Olten');

-- Insert Workouts
INSERT INTO workout (workout_id, workout_name, workout_date, duration, fk_user_id, fk_location_id)
VALUES 
    (1, 'Morning Workout', '2024-04-01', 60, 9999, 1), -- User 1 in Brugg
    (2, 'Afternoon Workout', '2024-04-01', 60, 9998, 2), -- User 2 in Basel
    (3, 'Evening Workout', '2024-04-01', 60, 9997, 3), -- User 3 in Olten
    (4, 'Morning Workout', '2024-04-08', 60, 9999, 1), -- User 1 in Brugg
    (5, 'Afternoon Workout', '2024-04-08', 60, 9998, 2), -- User 2 in Basel
    (6, 'Evening Workout', '2024-04-08', 60, 9997, 3); -- User 3 in Olten

-- Insert Exercises
INSERT INTO exercise (exercise_id, exercise_name, exercise_description, weight, repetition, number_of_sets, time, distance)
VALUES 
    (1, 'Bench Press', 'Chest exercise using a barbell', 50, 10, 3, NULL, NULL),
    (2, 'Burpees', 'Full-body exercise', NULL, 20, 3, NULL, NULL),
    (3, 'Rowing', 'Cardio exercise using a rowing machine', NULL, NULL, NULL, 20, 500),
    (4, 'Mountain Climbers', 'Full-body exercise', NULL, 30, 3, NULL, NULL),
    (5, 'Pull Ups', 'Upper body exercise using a pull-up bar', 0, 10, 3, NULL, NULL),
    (6, 'Push Ups', 'Upper body exercise using body weight', 0, 15, 3, NULL, NULL),
    (7, 'Skull Crushers', 'Triceps exercise using dumbbells or a barbell', 20, 12, 3, NULL, NULL),
    (8, 'Swimming', 'Full-body cardio exercise in water', NULL, NULL, NULL, 30, 500),
    (9, 'Dead Lift', 'Compound exercise targeting multiple muscle groups', 70, 8, 3, NULL, NULL),
    (10, 'Skip Rope', 'Cardio exercise using a jump rope', NULL, NULL, 3, 180, NULL),
    (11, 'Arnold Press', 'Shoulder exercise using dumbbells', 15, 10, 3, NULL, NULL),
    (12, 'Biceps Curl', 'Biceps exercise using dumbbells or a barbell', 10, 12, 3, NULL, NULL),
    (13, 'Thrusters', 'Full-body exercise combining a squat and overhead press', 25, 8, 3, NULL, NULL),
    (14, 'Chest Flys', 'Chest exercise using dumbbells or a cable machine', 20, 12, 3, NULL, NULL),
    (15, 'Tricep Extension', 'Triceps exercise using a dumbbell or cable machine', 15, 12, 3, NULL, NULL),
    (16, 'Lat Pull', 'Back exercise using a lat pulldown machine', 40, 10, 3, NULL, NULL),
    (17, 'Running', 'Cardio exercise outdoors or on a treadmill', NULL, NULL, NULL, 30, 500),
    (18, 'Sprints', 'High-intensity interval running', NULL, NULL, 3, 60, 100),
    (19, 'Box Jumps', 'Plyometric exercise using a box or platform', 0, 10, 3, NULL, NULL);

-- Assign Exercises to Workouts
INSERT INTO workouts_exercises (fk_workout_id, fk_exercise_id)
VALUES 
    (1, 1), (1, 2), (1, 3), -- User 1's Morning Workout in Brugg
    (2, 4), (2, 5), (2, 6), -- User 2's Afternoon Workout in Basel
    (3, 7), (3, 8), (3, 9); -- User 3's Evening Workout in Olten
