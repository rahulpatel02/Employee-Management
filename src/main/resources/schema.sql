-- Employee Management System Database Schema
-- Created: June 12, 2026

-- ============================================
-- Create Database
-- ============================================
CREATE DATABASE IF NOT EXISTS employee_management;
USE employee_management;

-- ============================================
-- Roles Table
-- ============================================
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT uk_role_name UNIQUE (name),
    INDEX idx_role_name (name)
);

-- ============================================
-- Users Table
-- ============================================
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uk_user_username UNIQUE (username),
    CONSTRAINT uk_user_email UNIQUE (email),
    INDEX idx_user_email (email),
    INDEX idx_user_username (username),
    INDEX idx_user_enabled (enabled)
);

-- ============================================
-- User Roles Junction Table (Many-to-Many)
-- ============================================
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_role_id (role_id)
);

-- ============================================
-- Employees Table
-- ============================================
CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uk_employee_email UNIQUE (email),
    INDEX idx_employee_email (email),
    INDEX idx_employee_department (department),
    INDEX idx_employee_status (status),
    INDEX idx_employee_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Sample Data
-- ============================================

-- Insert Roles
INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_MANAGER'),
('ROLE_EMPLOYEE');

-- Insert Sample Users
INSERT INTO users (username, email, password, enabled) VALUES
('admin', 'admin@company.com', '$2a$10$slYQmyNdGzin7olVN3ltCOYzuUVmMQkqq7/JH7.7o4nF1jlNzLwha', TRUE),
('user1', 'user1@company.com', '$2a$10$slYQmyNdGzin7olVN3ltCOYzuUVmMQkqq7/JH7.7o4nF1jlNzLwha', TRUE),
('manager1', 'manager1@company.com', '$2a$10$slYQmyNdGzin7olVN3ltCOYzuUVmMQkqq7/JH7.7o4nF1jlNzLwha', TRUE);

-- Assign Roles to Users
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),  -- admin has ADMIN role
(2, 2),  -- user1 has USER role
(3, 3);  -- manager1 has MANAGER role

-- Insert Sample Employees
INSERT INTO employees (name, email, department, salary, status) VALUES
('John Smith', 'john.smith@company.com', 'Engineering', 85000.00, 'ACTIVE'),
('Jane Doe', 'jane.doe@company.com', 'Sales', 75000.00, 'ACTIVE'),
('Bob Johnson', 'bob.johnson@company.com', 'HR', 70000.00, 'ACTIVE'),
('Alice Williams', 'alice.williams@company.com', 'Finance', 80000.00, 'INACTIVE'),
('Charlie Brown', 'charlie.brown@company.com', 'Engineering', 90000.00, 'ACTIVE');

