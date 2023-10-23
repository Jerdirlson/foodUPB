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

DROP TABLE IF EXISTS `tbl_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cliente` (
  `idtbl_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(45) DEFAULT NULL,
  `numero_cliente` varchar(45) DEFAULT NULL,
  `vip` tinyint(1) DEFAULT NULL,
  `direccion_cliente` int NOT NULL,
  PRIMARY KEY (`idtbl_cliente`),
  KEY `fk_tbl_cliente_tbl_direccion1_idx` (`direccion_cliente`),
  CONSTRAINT `tbl_cliente_ibfk_1` FOREIGN KEY (`direccion_cliente`) REFERENCES `tbl_direccion` (`idtbl_direccion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cliente`
--

LOCK TABLES `tbl_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
INSERT INTO `tbl_cliente` VALUES (1,'Jerdirlson Santamaria','3133662807',1,1),(3,'Diegogo','3173542133',1,4),(4,'ANGIE','313123213',1,5),(5,'Valentina','123',0,6);
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_direccion`
--

DROP TABLE IF EXISTS `tbl_direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_direccion` (
  `idtbl_direccion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(225) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `barrio` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `casa` tinyint DEFAULT NULL,
  PRIMARY KEY (`idtbl_direccion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_direccion`
--

LOCK TABLES `tbl_direccion` WRITE;
/*!40000 ALTER TABLE `tbl_direccion` DISABLE KEYS */;
INSERT INTO `tbl_direccion` VALUES (1,'8','16','Santa Ana','Santander',1),(4,'8','16','San Bernardo','Santander',1),(5,'8','12','San bernardo','Floridablanca',0),(6,'8','13','Villabel','Floridablanca',0);
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
  KEY `fk_tbl_factura_tbl_cliente1_idx` (`tbl_cliente_idtbl_cliente`)
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
-- Table structure for table `tbl_pedido`
--

DROP TABLE IF EXISTS `tbl_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pedido` (
  `idtbl_pedido` int NOT NULL AUTO_INCREMENT,
  `tbl_cliente_idtbl_cliente` int NOT NULL,
  PRIMARY KEY (`idtbl_pedido`,`tbl_cliente_idtbl_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pedido`
--

LOCK TABLES `tbl_pedido` WRITE;
/*!40000 ALTER TABLE `tbl_pedido` DISABLE KEYS */;
INSERT INTO `tbl_pedido` VALUES (7,1),(8,3),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1);
/*!40000 ALTER TABLE `tbl_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pedido_producto`
--

DROP TABLE IF EXISTS `tbl_pedido_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_pedido_producto` (
  `tbl_pedido_idtbl_pedido` int NOT NULL,
  `tbl_pedido_tbl_cliente_idtbl_cliente` int NOT NULL,
  `tbl_producto_idtbl_producto` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pedido_producto`
--

LOCK TABLES `tbl_pedido_producto` WRITE;
/*!40000 ALTER TABLE `tbl_pedido_producto` DISABLE KEYS */;
INSERT INTO `tbl_pedido_producto` VALUES (7,1,1),(7,1,1),(7,1,1),(8,3,2),(8,3,3),(8,3,5),(8,3,1),(9,1,2),(9,1,2),(9,1,2),(9,1,2),(9,1,3),(9,1,5),(10,1,1),(10,1,2),(10,1,3),(10,1,5),(10,1,4),(10,1,1),(10,1,1),(10,1,1),(11,1,1),(11,1,2),(11,1,3),(11,1,4),(11,1,5),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(12,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(13,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(14,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(15,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(16,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(17,1,3),(18,1,3),(18,1,3),(18,1,3),(19,1,3),(19,1,3),(19,1,3),(20,1,3),(20,1,3),(20,1,3),(21,1,3),(21,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(22,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(23,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(24,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(25,1,3),(26,1,7),(26,1,7),(26,1,7),(26,1,7),(27,1,7),(27,1,7),(27,1,7),(27,1,7),(28,1,2),(28,1,1),(28,1,3),(29,1,2),(29,1,3),(29,1,5),(30,1,2),(30,1,3),(30,1,5),(31,1,2),(31,1,3),(31,1,5),(32,1,1),(32,1,2),(32,1,3),(32,1,4),(33,1,1),(33,1,3),(33,1,2),(33,1,4),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(34,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(35,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(36,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(37,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,3),(38,1,1),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,3),(39,1,1),(39,1,7),(39,1,7),(39,1,7),(40,1,7),(40,1,7),(40,1,7),(40,1,7),(41,1,7),(41,1,7),(42,1,7),(42,1,7),(42,1,7),(42,1,7),(43,1,7),(43,1,7),(44,1,7),(44,1,7),(44,1,7),(44,1,7),(44,1,7),(45,1,7),(45,1,7),(45,1,7),(45,1,7),(45,1,7),(45,1,1);
/*!40000 ALTER TABLE `tbl_pedido_producto` ENABLE KEYS */;
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
  `tiempo_coccion` int DEFAULT NULL,
  PRIMARY KEY (`idtbl_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_producto`
--

LOCK TABLES `tbl_producto` WRITE;
/*!40000 ALTER TABLE `tbl_producto` DISABLE KEYS */;
INSERT INTO `tbl_producto` VALUES (1,'Hamburguesa','1',10000,'url',15),(2,'Perro','1',15000,'url',15),(3,'Salchipapa','1',5000,'url',10),(4,'Empanada','1',12000,'url',20),(5,'Picada','1',30000,'uri',30),(6,'Pincho','1',10000,'uri',15),(7,'epa','1',2000,'uri',25);
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
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtbl_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,'yeye','123','123');
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

-- Dump completed on 2023-10-23 11:57:57
