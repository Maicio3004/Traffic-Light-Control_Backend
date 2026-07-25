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
INSERT INTO route (id, name, latitude, longitude, location) VALUES (1, 'Carrera 27', '7.125947', '-73.118499', 'CARRERA');
INSERT INTO route (id, name, latitude, longitude, location) VALUES (2, 'Calle 36', '7.122493', '-73.114576', 'CALLE');

-- ======================================
-- INTERSECCIONES
-- ======================================

-- Ruta 1 - Carrera 27
-- Calle 45
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (1, 'Calle 45', 'INT-27-45', '7.117835', '-73.115376', 1);
-- Calle 41
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (2, 'Calle 41', 'INT-27-41', '7.11877', '-73.115576', 1);
-- Av La Rosita
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (3, 'Av La Rosita', 'INT-27-39', '7.119814', '-73.115977', 1);
-- Calle 36
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (4, 'Calle 36', 'INT-27-36', '7.121387', '-73.116578', 1);
-- Calle 35
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (5, 'Calle 35', 'INT-27-35', '7.1222', '-73.116822', 1);
-- Calle 34
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (6, 'Calle 34', 'INT-27-34', '7.122989', '-73.117114', 1);
-- Calle 33
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (7, 'Calle 33', 'INT-27-33', '7.123984', '-73.117579', 1);
-- Calle 32
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (8, 'Calle 32', 'INT-27-32', '7.124752', '-73.117943', 1);

-- Ruta 2 - Calle 36
-- Carrera 28
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (9, 'Carrera 28', 'INT-28-36', '7.121971', '-73.115368', 2);
-- Carrera 25
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (10, 'Carrera 25', 'INT-25-36', '7.120776', '-73.118218', 2);
-- Carrera 24
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (11, 'Carrera 24', 'INT-24-36', '7.120485', '-73.119050', 2);
-- Carrera 23
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (12, 'Carrera 23', 'INT-23-36', '7.120186', '-73.119884', 2);
-- Carrera 22
INSERT INTO intersection (id, location, code, latitude, longitude, route_id) VALUES (13, 'Carrera 22', 'INT-22-36', '7.119901', '-73.120703', 2);


INSERT INTO users (id, email, name, last_name, password, phone, role) VALUES (1, 'admin@gmail.com', 'Cristian', 'Ramirez', 'admin123', '300154789', 'ADMIN');
INSERT INTO users (id, email, name, last_name, password, phone, role) VALUES (2, 'operador@gmail.com', 'Andrea', 'Ardila', 'operador123', '3565621456', 'OPERATOR');
