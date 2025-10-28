-- Create the database (if it does not exist)
CREATE DATABASE IF NOT EXISTS employee_details;

-- Switch to the database
USE employee_details;

-- Create the table
CREATE TABLE IF NOT EXISTS employee (
    emp_id INT PRIMARY KEY,
    name VARCHAR(50),
    salary INT
);

-- Insert sample data
INSERT INTO employee (emp_id, name, salary) VALUES
(1, 'Rahul', 50000),
(2, 'kunal', 60000),
(3, 'Amit', 55000),
(4, 'Sujit', 52000),
(5, 'Nikhil', 62000);
