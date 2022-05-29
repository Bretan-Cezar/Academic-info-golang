SELECT * FROM faculty
SELECT * FROM teacher
SELECT * FROM year_of_study
SELECT * FROM curriculum
SELECT * FROM student
SELECT * FROM curriculum_discipline_distribution
SELECT * FROM discipline
SELECT * FROM optional_discipline
SELECT * FROM optionals_selection


UPDATE optional_discipline SET current_attendants = 0;
UPDATE optional_discipline SET approved = false WHERE optional_discipline_id = 13 OR optional_discipline_id = 20;
UPDATE optional_discipline SET max_attendants = 0 WHERE optional_discipline_id = 13 OR optional_discipline_id = 20;


DELETE FROM grade;
DELETE FROM curriculum_discipline_distribution WHERE discipline_id = 13 OR discipline_id = 20;
DELETE FROM optional_discipline WHERE optional_discipline_id = 36;
DELETE FROM discipline WHERE discipline_id = 36;
DELETE FROM optionals_selection;
