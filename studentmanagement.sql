CREATE DATABASE student_management;
use student_management;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    department VARCHAR(50)
);

CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    duration VARCHAR(50),
    fee DECIMAL(10,2)
);

CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    enrollment_date DATE,

    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

USE student_management;

CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    attendance_date DATE,
    status VARCHAR(20),

    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

USE student_management;

CREATE TABLE marks (
    mark_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    subject VARCHAR(100),
    marks INT,

    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

INSERT INTO courses (course_name, duration, fee)
VALUES
('Java Full Stack', '6 Months', 35000),
('Python Full Stack', '6 Months', 32000),
('Web Development', '4 Months', 25000);

INSERT INTO marks (student_id, subject, marks)
VALUES
(1, 'SQL', 75),
(1, 'Python', 90);

DELETE FROM attendance
WHERE attendance_id = 3;

ALTER TABLE attendance
ADD CONSTRAINT unique_student_date
UNIQUE (student_id, attendance_date);

SELECT * from students;

Select * from courses;

Select * from enrollments;

Select * From attendance;

SELECT * FROM marks;