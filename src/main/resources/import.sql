CREATE TABLE IF NOT EXISTS schema_version (id BIGINT AUTO_INCREMENT PRIMARY KEY, type VARCHAR(100) NOT NULL, version VARCHAR(50), description VARCHAR(255), executed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

-- ======================================
-- RUTA PRINCIPAL - 2
-- ======================================

-- Insertar la ruta
INSERT INTO route (id, name, latitude, longitude) VALUES (1, 'Ruta Principal - 1', '7.125947', '-73.118499');

-- ======================================
-- INTERSECCIONES
-- ======================================

-- Calle 45
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (2, 'Calle 45', 'INT-2-1', '7.117835', '-73.115376', 1, 1);
-- Calle 41
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (10, 'Calle 41', 'INT-2-2', '7.11877', '-73.115576', 1, 2);
-- Av La Rosita
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (11, 'Av La Rosita', 'INT-2-3', '7.119814', '-73.115977', 1, 3);
-- Calle 36
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (12, 'Calle 36', 'INT-2-4', '7.121387', '-73.116578', 1, 4);
-- Calle 35
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (13, 'Calle 35', 'INT-2-5', '7.1222', '-73.116822', 1, 5);
-- Calle 34
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (14, 'Calle 34', 'INT-2-6', '7.122989', '-73.117114', 1, 6);
-- Calle 33
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (15, 'Calle 33', 'INT-2-7', '7.123984', '-73.117579', 1, 7);
-- Calle 32
INSERT INTO Intersection (id, location, code, latitude, longitude, route_id, position) VALUES (16, 'Calle 32', 'INT-2-8', '7.124752', '-73.117943', 1, 8);

-- =====================================
-- SEMÁFOROS (Traffic_Light)
-- Cada intersección tendrá 4 semáforos (una por dirección)
-- ======================================

-- Para Calle 45
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (33, 'NS', 2);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (34, 'SN', 2);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (35, 'EW', 2);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (36, 'WE', 2);

-- Para Calle 41
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (37, 'NS', 10);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (38, 'SN', 10);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (39, 'EW', 10);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (40, 'WE', 10);

-- Para Av La Rosita
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (41, 'NS', 11);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (42, 'SN', 11);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (43, 'EW', 11);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (44, 'WE', 11);

-- Para Calle 36
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (45, 'NS', 12);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (46, 'SN', 12);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (47, 'EW', 12);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (48, 'WE', 12);

-- Para Calle 35
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (49, 'NS', 13);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (50, 'SN', 13);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (51, 'EW', 13);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (52, 'WE', 13);

-- Para Calle 34
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (53, 'NS', 14);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (54, 'SN', 14);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (55, 'EW', 14);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (56, 'WE', 14);

-- Para Calle 33
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (57, 'NS', 15);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (58, 'SN', 15);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (59, 'EW', 15);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (60, 'WE', 15);

-- Para Calle 32
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (61, 'NS', 16);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (62, 'SN', 16);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (63, 'EW', 16);
INSERT INTO Traffic_Light (id, direction, intersection_id) VALUES (64, 'WE', 16);

-- ======================================
-- Modos de operacion
-- ======================================

INSERT INTO operation_mode (id, description, mode_operation) VALUES (1, 'Modo apagado', 'OFF');
INSERT INTO operation_mode (id, description, mode_operation) VALUES (2, 'Modo normal', 'NORMAL');
INSERT INTO operation_mode (id, description, mode_operation) VALUES (3, 'Modo hora pico', 'PEAK');
