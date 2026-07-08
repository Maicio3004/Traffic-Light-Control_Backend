CREATE TABLE IF NOT EXISTS schema_version (id BIGINT AUTO_INCREMENT PRIMARY KEY, type VARCHAR(100) NOT NULL, version VARCHAR(50), description VARCHAR(255), executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

-- ======================================
-- Modos de operacion
-- ======================================

INSERT INTO operation_mode (id, description, mode_operation) VALUES (1, 'Modo apagado', 'OFF');
INSERT INTO operation_mode (id, description, mode_operation) VALUES (2, 'Modo normal', 'NORMAL');
INSERT INTO operation_mode (id, description, mode_operation) VALUES (3, 'Modo hora pico', 'PEAK');

-- ======================================
-- RUTA PRINCIPAL - 2
-- ======================================

-- Insertar la ruta
INSERT INTO route (id, name, latitude, longitude) VALUES (1, 'Ruta - Carrera 27', '7.125947', '-73.118499');

-- ======================================
-- INTERSECCIONES
-- ======================================

-- Calle 45
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (2, 'Calle 45', 'INT-2-1', '7.117835', '-73.115376', 1, 1);
-- Calle 41
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (10, 'Calle 41', 'INT-2-2', '7.11877', '-73.115576', 1, 2);
-- Av La Rosita
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (11, 'Av La Rosita', 'INT-2-3', '7.119814', '-73.115977', 1, 3);
-- Calle 36
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (12, 'Calle 36', 'INT-2-4', '7.121387', '-73.116578', 1, 4);
-- Calle 35
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (13, 'Calle 35', 'INT-2-5', '7.1222', '-73.116822', 1, 5);
-- Calle 34
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (14, 'Calle 34', 'INT-2-6', '7.122989', '-73.117114', 1, 6);
-- Calle 33
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (15, 'Calle 33', 'INT-2-7', '7.123984', '-73.117579', 1, 7);
-- Calle 32
INSERT INTO intersection (id, location, code, latitude, longitude, route_id, position) VALUES (16, 'Calle 32', 'INT-2-8', '7.124752', '-73.117943', 1, 8);

INSERT INTO users (id, email, name, last_name, password, phone, role) VALUES (1, 'admin@gmail.com', 'Cristian', 'Ramirez', 'admin123', '300154789', 'ADMIN');
INSERT INTO users (id, email, name, last_name, password, phone, role) VALUES (2, 'operador@gmail.com', 'Andrea', 'Ardila', 'operador123', '3565621456', 'OPERATOR');
