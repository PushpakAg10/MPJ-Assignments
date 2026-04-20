-- ============================================================
-- Employee Registration System - Database Setup
-- Run this file ONCE before starting the application
-- MySQL 5.7+ / 8.x compatible
-- ============================================================

-- 1. Create database
CREATE DATABASE IF NOT EXISTS employee_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE employee_db;

-- 2. Create employees table
CREATE TABLE IF NOT EXISTS employees (
    id           INT          NOT NULL AUTO_INCREMENT,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    phone        VARCHAR(15)  NOT NULL,
    department   VARCHAR(50)  NOT NULL,
    designation  VARCHAR(50)  NOT NULL,
    salary       DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    joining_date DATE         NOT NULL,
    gender       VARCHAR(10)  NOT NULL,
    address      TEXT,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Sample data (optional - comment out if not needed)
INSERT INTO employees (first_name, last_name, email, phone, department, designation, salary, joining_date, gender, address)
VALUES
('Pushpak', 'Patil',  'pushpak@example.com',  '9876543210', 'IT',      'Senior Developer', 75000.00, '2023-06-01', 'Male',   'Pune, Maharashtra'),
('Priya',   'Sharma', 'priya@example.com',    '9123456780', 'HR',      'Manager',          65000.00, '2022-03-15', 'Female', 'Mumbai, Maharashtra'),
('Rahul',   'Verma',  'rahul@example.com',    '9988776655', 'Finance', 'Team Lead',        70000.00, '2021-09-01', 'Male',   'Bangalore, Karnataka');

-- ============================================================
-- Verification query
-- ============================================================
SELECT * FROM employees;
