-- Elimina la base de datos si existe
DROP DATABASE IF EXISTS seg;

-- Crea la base de datos
CREATE DATABASE IF NOT EXISTS seg;
USE seg;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(255), 
  role VARCHAR(50)
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  price DOUBLE
);

-- Inserta usuarios
INSERT INTO usuario (id, username, password, role) VALUES
 (1, 'julio', '$2a$12$NlZ4t4KH3CCwF/Q1JV26A..w2Sqn3ZM9qKg0GsVqaZaMhwp79gfym', 'ROLE_ADMIN'),
 (2, 'moises', '$2a$12$NlZ4t4KH3CCwF/Q1JV26A..w2Sqn3ZM9qKg0GsVqaZaMhwp79gfym', 'ROLE_USER');

-- Inserta un producto
INSERT INTO producto (id, name, price) VALUES
 (1, 'Inka kola', 6.30);


