-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-11-2024 a las 19:44:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdperegrinos_gabriel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carnet`
--

CREATE TABLE `carnet` (
  `id` int(11) NOT NULL,
  `id_parada_ini` int(11) NOT NULL,
  `fecha_exp` date NOT NULL,
  `distancia` float NOT NULL,
  `n_vips` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales_usuario`
--

CREATE TABLE `credenciales_usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `clave` varchar(30) NOT NULL,
  `tipo_perfil` enum('invitado','Responsable_parada','Peregrino','Administrador_general') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estancia`
--

CREATE TABLE `estancia` (
  `id` int(11) NOT NULL,
  `id_parada` int(11) NOT NULL,
  `id_peregrino` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `es_vip` tinyint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parada`
--

CREATE TABLE `parada` (
  `id` int(11) NOT NULL,
  `id_credenciales` int(11) NOT NULL,
  `nombre` int(11) NOT NULL,
  `region` int(11) NOT NULL,
  `nombre_responsable` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peregrino`
--

CREATE TABLE `peregrino` (
  `id` int(11) NOT NULL,
  `id_credenciales` int(11) NOT NULL,
  `id_carnet` int(11) NOT NULL,
  `nombre` int(11) NOT NULL,
  `nacionalidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sellado_en_parada`
--

CREATE TABLE `sellado_en_parada` (
  `id` int(11) NOT NULL,
  `id_peregrino` int(11) NOT NULL,
  `id_parada` int(11) NOT NULL,
  `fecha_de_sello` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carnet`
--
ALTER TABLE `carnet`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_parada_ini` (`id_parada_ini`);

--
-- Indices de la tabla `credenciales_usuario`
--
ALTER TABLE `credenciales_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estancia`
--
ALTER TABLE `estancia`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_parada` (`id_parada`),
  ADD UNIQUE KEY `id_peregrino` (`id_peregrino`);

--
-- Indices de la tabla `parada`
--
ALTER TABLE `parada`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_credenciales` (`id_credenciales`);

--
-- Indices de la tabla `peregrino`
--
ALTER TABLE `peregrino`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_credenciales` (`id_credenciales`),
  ADD UNIQUE KEY `id_carnet` (`id_carnet`);

--
-- Indices de la tabla `sellado_en_parada`
--
ALTER TABLE `sellado_en_parada`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_peregrino` (`id_peregrino`),
  ADD UNIQUE KEY `id_parada` (`id_parada`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carnet`
--
ALTER TABLE `carnet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `credenciales_usuario`
--
ALTER TABLE `credenciales_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estancia`
--
ALTER TABLE `estancia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `peregrino`
--
ALTER TABLE `peregrino`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sellado_en_parada`
--
ALTER TABLE `sellado_en_parada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carnet`
--
ALTER TABLE `carnet`
  ADD CONSTRAINT `carnet_ibfk_1` FOREIGN KEY (`id_parada_ini`) REFERENCES `parada` (`id`);

--
-- Filtros para la tabla `estancia`
--
ALTER TABLE `estancia`
  ADD CONSTRAINT `estancia_ibfk_1` FOREIGN KEY (`id_parada`) REFERENCES `parada` (`id`),
  ADD CONSTRAINT `estancia_ibfk_2` FOREIGN KEY (`id_peregrino`) REFERENCES `peregrino` (`id`);

--
-- Filtros para la tabla `parada`
--
ALTER TABLE `parada`
  ADD CONSTRAINT `parada_ibfk_1` FOREIGN KEY (`id_credenciales`) REFERENCES `credenciales_usuario` (`id`);

--
-- Filtros para la tabla `peregrino`
--
ALTER TABLE `peregrino`
  ADD CONSTRAINT `peregrino_ibfk_1` FOREIGN KEY (`id_credenciales`) REFERENCES `credenciales_usuario` (`id`);

--
-- Filtros para la tabla `sellado_en_parada`
--
ALTER TABLE `sellado_en_parada`
  ADD CONSTRAINT `sellado_en_parada_ibfk_1` FOREIGN KEY (`id_peregrino`) REFERENCES `peregrino` (`id`),
  ADD CONSTRAINT `sellado_en_parada_ibfk_2` FOREIGN KEY (`id_parada`) REFERENCES `parada` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
