DROP DATABASE IF EXISTS aceituna_db;

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS aceituna_db;
USE aceituna_db;

-- Tabla Trabajador
CREATE TABLE Trabajador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    puesto VARCHAR(50),
    salario DECIMAL(10, 2)
);

-- Tabla Cuadrilla
CREATE TABLE Cuadrilla (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    supervisor_id INT,
    FOREIGN KEY (supervisor_id) REFERENCES Trabajador(id) ON DELETE SET NULL
);

-- Tabla Olivar
CREATE TABLE Olivar (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(100) NOT NULL,
    hectareas DECIMAL(5, 2) NOT NULL,
    produccionAnual DECIMAL(10, 2) NOT NULL
);

-- Tabla Almazara
CREATE TABLE Almazara (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    capacidad DECIMAL(10, 2) NOT NULL
);

-- Tabla intermedia Cuadrilla_Olivar (relación muchos a muchos entre Cuadrilla y Olivar)
CREATE TABLE Cuadrilla_Olivar (
    cuadrilla_id INT,
    olivar_id INT,
    PRIMARY KEY (cuadrilla_id, olivar_id),
    FOREIGN KEY (cuadrilla_id) REFERENCES Cuadrilla(id) ON DELETE CASCADE,
    FOREIGN KEY (olivar_id) REFERENCES Olivar(id) ON DELETE CASCADE
);

-- Tabla intermedia Cuadrilla_Trabajador (relación muchos a muchos entre Cuadrilla y Trabajador)
CREATE TABLE Cuadrilla_Trabajador (
    cuadrilla_id INT,
    trabajador_id INT,
    PRIMARY KEY (cuadrilla_id, trabajador_id),
    FOREIGN KEY (cuadrilla_id) REFERENCES Cuadrilla(id) ON DELETE CASCADE,
    FOREIGN KEY (trabajador_id) REFERENCES Trabajador(id) ON DELETE CASCADE
);

-- Tabla Producción (registra la cantidad recolectada de una cuadrilla en un olivar en una fecha determinada)
CREATE TABLE Produccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cuadrilla_id INT NOT NULL,
    olivar_id INT NOT NULL,
    almazara_id INT NOT NULL,
    fecha DATE NOT NULL,
    cantidadRecolectada DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cuadrilla_id) REFERENCES Cuadrilla(id) ON DELETE CASCADE,
    FOREIGN KEY (olivar_id) REFERENCES Olivar(id) ON DELETE CASCADE,
    FOREIGN KEY (almazara_id) REFERENCES Almazara(id) ON DELETE CASCADE
);
