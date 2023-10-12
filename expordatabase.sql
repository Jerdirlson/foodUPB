-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: estructuras-database
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_cliente`
--

CREATE DATABASE IF NOT EXISTS `estructuras-database`;
USE `estructuras-database`;

DROP TABLE IF EXISTS `tbl_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cliente` (
  `idtbl_cliente` int NOT NULL,
  `nombre_cliente` varchar(45) DEFAULT NULL,
  `numero_cliente` varchar(45) DEFAULT NULL,
  `vip` tinyint(1) DEFAULT NULL,
  `direccion_cliente` int NOT NULL,
  PRIMARY KEY (`idtbl_cliente`),
  KEY `fk_tbl_cliente_tbl_direccion1_idx` (`direccion_cliente`),
  CONSTRAINT `fk_tbl_cliente_tbl_direccion1` FOREIGN KEY (`direccion_cliente`) REFERENCES `tbl_direccion` (`idtbl_direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cliente`
--

LOCK TABLES `tbl_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
INSERT INTO `tbl_cliente` VALUES (1,'Jerdirlson Santamaria','3133662807',1,1);
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_direccion`
--

DROP TABLE IF EXISTS `tbl_direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_direccion` (
  `idtbl_direccion` int NOT NULL,
  `calle` varchar(225) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `barrio` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `casa` tinyint DEFAULT NULL,
  PRIMARY KEY (`idtbl_direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_direccion`
--

LOCK TABLES `tbl_direccion` WRITE;
/*!40000 ALTER TABLE `tbl_direccion` DISABLE KEYS */;
INSERT INTO `tbl_direccion` VALUES (1,'8','16','Santa Ana','Santander',1);
/*!40000 ALTER TABLE `tbl_direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_empresa`
--

DROP TABLE IF EXISTS `tbl_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_empresa` (
  `idtbl_empresa` int NOT NULL,
  `nombre_empresa` varchar(45) DEFAULT NULL,
  `direccion_empresa` varchar(45) DEFAULT NULL,
  `logo_empresa` blob,
  PRIMARY KEY (`idtbl_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_empresa`
--

LOCK TABLES `tbl_empresa` WRITE;
/*!40000 ALTER TABLE `tbl_empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_factura`
--

DROP TABLE IF EXISTS `tbl_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_factura` (
  `num_factura` int NOT NULL,
  `fecha_factura` datetime DEFAULT NULL,
  `impuesto` varchar(45) DEFAULT NULL,
  `tbl_cliente_idtbl_cliente` int NOT NULL,
  `tbl_usuario_idtbl_usuario` int NOT NULL,
  `tbl_empresa_idtbl_empresa` int NOT NULL,
  `valor_final` int DEFAULT NULL,
  PRIMARY KEY (`num_factura`),
  KEY `fk_tbl_factura_tbl_cliente1_idx` (`tbl_cliente_idtbl_cliente`),
  KEY `fk_tbl_factura_tbl_usuario1_idx` (`tbl_usuario_idtbl_usuario`),
  KEY `fk_tbl_factura_tbl_empresa1_idx` (`tbl_empresa_idtbl_empresa`),
  CONSTRAINT `fk_tbl_factura_tbl_cliente1` FOREIGN KEY (`tbl_cliente_idtbl_cliente`) REFERENCES `tbl_cliente` (`idtbl_cliente`),
  CONSTRAINT `fk_tbl_factura_tbl_empresa1` FOREIGN KEY (`tbl_empresa_idtbl_empresa`) REFERENCES `tbl_empresa` (`idtbl_empresa`),
  CONSTRAINT `fk_tbl_factura_tbl_usuario1` FOREIGN KEY (`tbl_usuario_idtbl_usuario`) REFERENCES `tbl_usuario` (`idtbl_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_factura`
--

LOCK TABLES `tbl_factura` WRITE;
/*!40000 ALTER TABLE `tbl_factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_factura_has_tbl_producto`
--

DROP TABLE IF EXISTS `tbl_factura_has_tbl_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_factura_has_tbl_producto` (
  `tbl_factura_num_factura` int NOT NULL,
  `tbl_producto_idtbl_producto` int NOT NULL,
  PRIMARY KEY (`tbl_factura_num_factura`,`tbl_producto_idtbl_producto`),
  KEY `fk_tbl_factura_has_tbl_producto_tbl_producto1_idx` (`tbl_producto_idtbl_producto`),
  KEY `fk_tbl_factura_has_tbl_producto_tbl_factura_idx` (`tbl_factura_num_factura`),
  CONSTRAINT `fk_tbl_factura_has_tbl_producto_tbl_factura` FOREIGN KEY (`tbl_factura_num_factura`) REFERENCES `tbl_factura` (`num_factura`),
  CONSTRAINT `fk_tbl_factura_has_tbl_producto_tbl_producto1` FOREIGN KEY (`tbl_producto_idtbl_producto`) REFERENCES `tbl_producto` (`idtbl_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_factura_has_tbl_producto`
--

LOCK TABLES `tbl_factura_has_tbl_producto` WRITE;
/*!40000 ALTER TABLE `tbl_factura_has_tbl_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_factura_has_tbl_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_producto`
--

DROP TABLE IF EXISTS `tbl_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_producto` (
  `idtbl_producto` int NOT NULL,
  `nombre_producto` varchar(45) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  `precio_unitario` int DEFAULT NULL,
  `uri_img` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtbl_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_producto`
--

LOCK TABLES `tbl_producto` WRITE;
/*!40000 ALTER TABLE `tbl_producto` DISABLE KEYS */;
INSERT INTO `tbl_producto` VALUES (1,'Hamburguesa','1',10000,'url'),(2,'Perro','1',15000,'url'),(3,'Salchipapa','1',5000,'url'),(4,'Empanada','1',12000,'url'),(5,'Picada','1',30000,'uri'),(6,'Pincho','1',10000,'uri'),(7,'epa','1',2000,'uri');
/*!40000 ALTER TABLE `tbl_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario` (
  `idtbl_usuario` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `adress` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type_user` int DEFAULT NULL,
  PRIMARY KEY (`idtbl_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,'yeye','123',111,'111','123',2);
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-11 20:34:39
