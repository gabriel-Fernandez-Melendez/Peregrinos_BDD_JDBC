-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-11-2024 a las 11:20:08
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdperegrinos_gabrielfernandez`
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

--
-- Volcado de datos para la tabla `carnet`
--

INSERT INTO `carnet` (`id`, `id_parada_ini`, `fecha_exp`, `distancia`, `n_vips`) VALUES
(1, 1, '2024-11-12', 5, 1),
(2, 2, '2024-11-12', 0, 0),
(3, 3, '2024-11-12', 0, 0),
(4, 4, '2024-11-12', 0, 0),
(5, 5, '2024-11-12', 0, 0);

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

--
-- Volcado de datos para la tabla `credenciales_usuario`
--

INSERT INTO `credenciales_usuario` (`id`, `nombre`, `clave`, `tipo_perfil`) VALUES
(1, 'gabo', 'gabo', 'Peregrino'),
(2, 'peregrino', 'peregrino', 'Peregrino'),
(3, 'jose', 'jose', 'Peregrino'),
(4, 'pedro', 'pedro', 'Peregrino'),
(5, 'caminante', 'caminante', 'Peregrino'),
(6, 'paradauno', 'parada', 'Responsable_parada'),
(7, 'paradados', 'parada', 'Responsable_parada'),
(8, 'paradatres', 'parada', 'Responsable_parada'),
(9, 'paradacuatro', 'parada', 'Responsable_parada'),
(10, 'paradacinco', 'parada', 'Responsable_parada'),
(11, 'admin', 'admin', 'Administrador_general');

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

--
-- Volcado de datos para la tabla `estancia`
--

INSERT INTO `estancia` (`id`, `id_parada`, `id_peregrino`, `fecha`, `es_vip`) VALUES
(1, 1, 1, '2024-11-27', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parada`
--

CREATE TABLE `parada` (
  `id` int(11) NOT NULL,
  `id_credenciales` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `region` char(1) NOT NULL,
  `nombre_responsable` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `parada`
--

INSERT INTO `parada` (`id`, `id_credenciales`, `nombre`, `region`, `nombre_responsable`) VALUES
(1, 6, 'Pedrusco Gordo', 'P', 'gonzalo'),
(2, 7, 'ACDC Street', 'A', 'compadre'),
(3, 8, 'Casi Santiago', 'S', 'julia'),
(4, 9, 'Coruña', 'C', 'carla'),
(5, 10, 'Ruta Maritima', 'R', 'ricardo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peregrino`
--

CREATE TABLE `peregrino` (
  `id` int(11) NOT NULL,
  `id_credenciales` int(11) NOT NULL,
  `id_carnet` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `nacionalidad` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peregrino`
--

INSERT INTO `peregrino` (`id`, `id_credenciales`, `id_carnet`, `nombre`, `nacionalidad`) VALUES
(1, 1, 1, 'gabriel', 'Australia'),
(2, 2, 2, 'peregrino misterioso', 'Alemania'),
(3, 3, 3, 'jose', 'Francia'),
(4, 4, 4, 'pedro', 'Canadá'),
(5, 5, 5, 'andante profesional', 'Bélgica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sellado_en_parada`
--

CREATE TABLE `sellado_en_parada` (
  `id` int(11) NOT NULL,
  `id_peregrino` int(11) NOT NULL,
  `id_parada` int(11) NOT NULL,
  `fecha_de_sello` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sellado_en_parada`
--

INSERT INTO `sellado_en_parada` (`id`, `id_peregrino`, `id_parada`, `fecha_de_sello`) VALUES
(1, 1, 1, '2024-11-27');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carnet`
--
ALTER TABLE `carnet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_parada_ini` (`id_parada_ini`) USING BTREE;

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
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `id_credenciales` (`id_credenciales`);

--
-- Indices de la tabla `peregrino`
--
ALTER TABLE `peregrino`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_credenciales` (`id_credenciales`) USING BTREE,
  ADD KEY `id_carnet` (`id_carnet`) USING BTREE;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `credenciales_usuario`
--
ALTER TABLE `credenciales_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `estancia`
--
ALTER TABLE `estancia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `parada`
--
ALTER TABLE `parada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `peregrino`
--
ALTER TABLE `peregrino`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `sellado_en_parada`
--
ALTER TABLE `sellado_en_parada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  ADD CONSTRAINT `estancia_ibfk_2` FOREIGN KEY (`id_peregrino`) REFERENCES `peregrino` (`id`),
  ADD CONSTRAINT `estancia_ibfk_3` FOREIGN KEY (`id_parada`) REFERENCES `parada` (`id`);

--
-- Filtros para la tabla `parada`
--
ALTER TABLE `parada`
  ADD CONSTRAINT `parada_ibfk_1` FOREIGN KEY (`id_credenciales`) REFERENCES `credenciales_usuario` (`id`);

--
-- Filtros para la tabla `peregrino`
--
ALTER TABLE `peregrino`
  ADD CONSTRAINT `peregrino_ibfk_1` FOREIGN KEY (`id_credenciales`) REFERENCES `credenciales_usuario` (`id`),
  ADD CONSTRAINT `peregrino_ibfk_2` FOREIGN KEY (`id_carnet`) REFERENCES `carnet` (`id`);

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
