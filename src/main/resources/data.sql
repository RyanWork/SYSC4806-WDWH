INSERT INTO category (name) VALUES ('Programming');
INSERT INTO CATEGORY (name) VALUES ('Software Architecture');
INSERT INTO CATEGORY (name) VALUES ('Logic Algorithm');

INSERT INTO LEARNING_OUTCOME (name, category_id) VALUES ('Programming', 1);
INSERT INTO LEARNING_OUTCOME (name, category_id) VALUES ('Design Software System', 2);
INSERT INTO LEARNING_OUTCOME (name, category_id) VALUES ('Logic Gates', 3);

INSERT INTO COURSE (year, name, code) VALUES (4, 'Software Engineering Lab', '4806');
INSERT INTO COURSE (year, name, code) VALUES (3, 'Real-Time Concurrent System', '3303');
INSERT INTO COURSE (year, name, code) VALUES (3, 'Operating Systems', '4001');
INSERT INTO COURSE (year, name, code) VALUES (2, 'Circuits and Signals', '2501');
INSERT INTO COURSE (year, name, code) VALUES (2, 'Switching Circuits', '2607');
INSERT INTO COURSE (year, name, code) VALUES (1, 'Introduction to Software', '1005');
INSERT INTO COURSE (year, name, code) VALUES (1, 'Introduction to Engineering', '1010');

INSERT INTO PROGRAM (name) VALUES ('Software Engineering');
INSERT INTO PROGRAM (name) VALUES ('Civil Engineering');
INSERT INTO PROGRAM (name) VALUES ('Computer System Engineering');
INSERT INTO PROGRAM (name) VALUES ('Electrical Engineering');

INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (1, 1);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (1, 2);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (1, 3);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (2, 1);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (3, 1);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (4, 3);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (5, 3);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (6, 1);
INSERT INTO COURSE_LEARNING_OUTCOMES (courses_id, learning_outcomes_id) VALUES (7, 2);

INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (1, 1);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (1, 2);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (1, 3);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (1, 4);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (1, 5);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (1, 6);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (2, 7);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (3, 5);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (3, 6);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (4, 4);
INSERT INTO PROGRAM_COURSES (programs_id, courses_id) VALUES (4, 5);