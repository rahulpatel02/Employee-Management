-- ============================================
-- INSERT 50 EMPLOYEES
-- ============================================
-- Database: employee_management
-- Table: employees
-- ============================================

INSERT INTO employees (name, email, department, salary, status) VALUES
('John Smith', 'john.smith@company.com', 'Engineering', 85000.00, 'ACTIVE'),
('Jane Doe', 'jane.doe@company.com', 'Sales', 75000.00, 'ACTIVE'),
('Bob Johnson', 'bob.johnson@company.com', 'HR', 70000.00, 'ACTIVE'),
('Alice Williams', 'alice.williams@company.com', 'Finance', 80000.00, 'INACTIVE'),
('Charlie Brown', 'charlie.brown@company.com', 'Engineering', 90000.00, 'ACTIVE'),
('Diana Martinez', 'diana.martinez@company.com', 'Marketing', 72000.00, 'ACTIVE'),
('Edward Lee', 'edward.lee@company.com', 'Engineering', 95000.00, 'ACTIVE'),
('Fiona White', 'fiona.white@company.com', 'Sales', 78000.00, 'ACTIVE'),
('George Taylor', 'george.taylor@company.com', 'Finance', 82000.00, 'ACTIVE'),
('Hannah Anderson', 'hannah.anderson@company.com', 'HR', 68000.00, 'ACTIVE'),
('Ian Thompson', 'ian.thompson@company.com', 'Engineering', 92000.00, 'ACTIVE'),
('Julia Garcia', 'julia.garcia@company.com', 'Marketing', 71000.00, 'ACTIVE'),
('Kevin Harris', 'kevin.harris@company.com', 'Sales', 76000.00, 'INACTIVE'),
('Laura Clark', 'laura.clark@company.com', 'Finance', 81000.00, 'ACTIVE'),
('Michael Lewis', 'michael.lewis@company.com', 'Engineering', 88000.00, 'ACTIVE'),
('Nancy Walker', 'nancy.walker@company.com', 'HR', 69000.00, 'ACTIVE'),
('Oscar Hall', 'oscar.hall@company.com', 'Sales', 77000.00, 'ACTIVE'),
('Patricia Allen', 'patricia.allen@company.com', 'Finance', 83000.00, 'ACTIVE'),
('Quincy Young', 'quincy.young@company.com', 'Engineering', 87000.00, 'ACTIVE'),
('Rachel King', 'rachel.king@company.com', 'Marketing', 73000.00, 'ACTIVE'),
('Samuel Wright', 'samuel.wright@company.com', 'Sales', 75000.00, 'ACTIVE'),
('Tanya Scott', 'tanya.scott@company.com', 'HR', 70000.00, 'INACTIVE'),
('Ulysses Green', 'ulysses.green@company.com', 'Engineering', 91000.00, 'ACTIVE'),
('Victoria Adams', 'victoria.adams@company.com', 'Finance', 79000.00, 'ACTIVE'),
('William Nelson', 'william.nelson@company.com', 'Marketing', 72000.00, 'ACTIVE'),
('Xena Carter', 'xena.carter@company.com', 'Sales', 76000.00, 'ACTIVE'),
('Yusuf Mitchell', 'yusuf.mitchell@company.com', 'Engineering', 89000.00, 'ACTIVE'),
('Zara Perez', 'zara.perez@company.com', 'HR', 71000.00, 'ACTIVE'),
('Aaron Roberts', 'aaron.roberts@company.com', 'Finance', 80000.00, 'ACTIVE'),
('Bella Phillips', 'bella.phillips@company.com', 'Sales', 74000.00, 'ACTIVE'),
('Curtis Campbell', 'curtis.campbell@company.com', 'Engineering', 86000.00, 'ACTIVE'),
('Donna Parker', 'donna.parker@company.com', 'Marketing', 73000.00, 'ACTIVE'),
('Ethan Evans', 'ethan.evans@company.com', 'Sales', 77000.00, 'INACTIVE'),
('Faye Edwards', 'faye.edwards@company.com', 'Finance', 82000.00, 'ACTIVE'),
('Gerald Collins', 'gerald.collins@company.com', 'Engineering', 93000.00, 'ACTIVE'),
('Hannah Reeves', 'hannah.reeves@company.com', 'HR', 68000.00, 'ACTIVE'),
('Ivan Morris', 'ivan.morris@company.com', 'Sales', 75000.00, 'ACTIVE'),
('Jasmine Murphy', 'jasmine.murphy@company.com', 'Finance', 81000.00, 'ACTIVE'),
('Kyle Rogers', 'kyle.rogers@company.com', 'Engineering', 88000.00, 'ACTIVE'),
('Lucia Morgan', 'lucia.morgan@company.com', 'Marketing', 72000.00, 'ACTIVE'),
('Marcus Peterson', 'marcus.peterson@company.com', 'Sales', 76000.00, 'ACTIVE'),
('Nora Powell', 'nora.powell@company.com', 'HR', 69000.00, 'ACTIVE'),
('Oliver Long', 'oliver.long@company.com', 'Engineering', 90000.00, 'INACTIVE'),
('Piper Patterson', 'piper.patterson@company.com', 'Finance', 80000.00, 'ACTIVE'),
('Quinn Hughes', 'quinn.hughes@company.com', 'Sales', 77000.00, 'ACTIVE'),
('Rosa Flores', 'rosa.flores@company.com', 'Marketing', 71000.00, 'ACTIVE'),
('Simon Washington', 'simon.washington@company.com', 'Engineering', 87000.00, 'ACTIVE'),
('Tina Butler', 'tina.butler@company.com', 'HR', 70000.00, 'ACTIVE'),
('Ulrich Simmons', 'ulrich.simmons@company.com', 'Sales', 75000.00, 'ACTIVE'),
('Venus Jackson', 'venus.jackson@company.com', 'Finance', 83000.00, 'ACTIVE');

-- ============================================
-- VERIFICATION QUERY
-- ============================================
-- To verify the insertions, run:
-- SELECT COUNT(*) as total_employees FROM employees;
-- SELECT * FROM employees ORDER BY id DESC LIMIT 10;

