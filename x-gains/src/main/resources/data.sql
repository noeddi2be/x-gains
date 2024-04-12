-- Insert Users
INSERT INTO account (account_type, username, email, salt, hashed_password, firstname, lastname)
VALUES 
    ("user", 'Emy', 'emily.sharp@gmail.com', NULL, '65fe608291e196976fcdeb2', 'Emily', 'Sharp'),
    ("user", 'Pete', 'pete.david@home.com', NULL, '242064d0d3f5405e4ca8245d67c65fe608291e196976fcdeb2', 'Pete', 'Davidson'),
    ("user", 'GigaChad50', 'gg5@icloud.com', NULL, '0d3f5405e4ca8245d67c65fe608291e196976fcdeb2', 'Bill', 'Gates');

-- Insert Locations
INSERT INTO location (location_name) VALUES ('Brugg'), ('Basel'), ('Olten');

-- Insert Workouts
INSERT INTO workout (workout_name, workout_date, duration, fk_user_id, fk_location_id)
VALUES 
    ('Morning Workout', '2024-04-01', 60, 1, 1), -- User 1 in Brugg
    ('Afternoon Workout', '2024-04-01', 60, 2, 2), -- User 2 in Basel
    ('Evening Workout', '2024-04-01', 60, 3, 3), -- User 3 in Olten
    ('Morning Workout', '2024-04-08', 60, 1, 1), -- User 1 in Brugg
    ('Afternoon Workout', '2024-04-08', 60, 2, 2), -- User 2 in Basel
    ('Evening Workout', '2024-04-08', 60, 3, 3); -- User 3 in Olten

-- Insert Exercises
INSERT INTO exercise (exercise_name, exercise_description, weight, repetition, number_of_sets, time, distance)
VALUES 
    ('Bench Press', 'Chest exercise using a barbell', 50, 10, 3, NULL, NULL),
    ('Burpees', 'Full-body exercise', NULL, 20, 3, NULL, NULL),
    ('Rowing', 'Cardio exercise using a rowing machine', NULL, NULL, NULL, 20, 500),
    ('Weighted Squats', 'Leg exercise using a barbell', 60, 12, 3, NULL, NULL),
    ('Mountain Climbers', 'Full-body exercise', NULL, 30, 3, NULL, NULL),
    ('Pull Ups', 'Upper body exercise using a pull-up bar', 0, 10, 3, NULL, NULL),
    ('Push Ups', 'Upper body exercise using body weight', 0, 15, 3, NULL, NULL),
    ('Skull Crushers', 'Triceps exercise using dumbbells or a barbell', 20, 12, 3, NULL, NULL),
    ('Swimming', 'Full-body cardio exercise in water', NULL, NULL, NULL, 30, 500),
    ('Dead Lift', 'Compound exercise targeting multiple muscle groups', 70, 8, 3, NULL, NULL),
    ('Skip Rope', 'Cardio exercise using a jump rope', NULL, NULL, 3, 180, NULL),
    ('Arnold Press', 'Shoulder exercise using dumbbells', 15, 10, 3, NULL, NULL),
    ('Biceps Curl', 'Biceps exercise using dumbbells or a barbell', 10, 12, 3, NULL, NULL),
    ('Thrusters', 'Full-body exercise combining a squat and overhead press', 25, 8, 3, NULL, NULL),
    ('Chest Flys', 'Chest exercise using dumbbells or a cable machine', 20, 12, 3, NULL, NULL),
    ('Tricep Extension', 'Triceps exercise using a dumbbell or cable machine', 15, 12, 3, NULL, NULL),
    ('Lat Pull', 'Back exercise using a lat pulldown machine', 40, 10, 3, NULL, NULL),
    ('Running', 'Cardio exercise outdoors or on a treadmill', NULL, NULL, NULL, 30, 500),
    ('Sprints', 'High-intensity interval running', NULL, NULL, 3, 60, 100),
    ('Box Jumps', 'Plyometric exercise using a box or platform', 0, 10, 3, NULL, NULL);

-- Assign Exercises to Workouts
INSERT INTO including (fk_workout_id, fk_exercise_id)
VALUES 
    (1, 1), (1, 2), (1, 3), -- User 1's Morning Workout in Brugg
    (2, 4), (2, 5), (2, 6), -- User 2's Afternoon Workout in Basel
    (3, 7), (3, 8), (3, 9); -- User 3's Evening Workout in Olten
