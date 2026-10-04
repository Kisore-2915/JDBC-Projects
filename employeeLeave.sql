CREATE DATABASE employee_leave_db;

USE employee_leave_db;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    department VARCHAR(50)
);

CREATE TABLE leave_balance (
    balance_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    total_leaves INT DEFAULT 12,
    leaves_taken INT DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE leave_requests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    leave_type VARCHAR(50),
    start_date DATE,
    end_date DATE,
    reason VARCHAR(255),
    status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

SELECT * FROM employees;

DELETE FROM employees;

ALTER TABLE employees AUTO_INCREMENT = 1;

SELECT * FROM leave_balance;

SELECT * FROM leave_requests;