-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema estructuras-database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estructuras-database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estructuras-database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `estructuras-database` ;

-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_usuario` (
  `idtbl_usuario` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `adress` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `type_user` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idtbl_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = 'Esta es la tabla donde se van a ';


-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_empresa` (
  `idtbl_empresa` INT NOT NULL,
  `nombre_empresa` VARCHAR(45) NULL,
  `direccion_empresa` VARCHAR(45) NULL,
  `logo_empresa` BLOB NULL,
  PRIMARY KEY (`idtbl_empresa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_direccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_direccion` (
  `idtbl_direccion` INT NOT NULL,
  `calle` VARCHAR(225) NULL,
  `numero` VARCHAR(45) NULL,
  `casa` VARCHAR(45) NULL,
  `barrio` VARCHAR(45) NULL,
  `municipio` VARCHAR(45) NULL,
  PRIMARY KEY (`idtbl_direccion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_cliente` (
  `idtbl_cliente` INT NOT NULL,
  `nombre_cliente` VARCHAR(45) NULL,
  `numero_cliente` VARCHAR(45) NULL,
  `vip` BIT(1) NULL,
  `direccion_cliente` INT NOT NULL,
  PRIMARY KEY (`idtbl_cliente`),
  INDEX `fk_tbl_cliente_tbl_direccion1_idx` (`direccion_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_cliente_tbl_direccion1`
    FOREIGN KEY (`direccion_cliente`)
    REFERENCES `estructuras-database`.`tbl_direccion` (`idtbl_direccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_factura` (
  `num_factura` INT NOT NULL,
  `fecha_factura` DATETIME NULL,
  `impuesto` VARCHAR(45) NULL,
  `tbl_cliente_idtbl_cliente` INT NOT NULL,
  `tbl_usuario_idtbl_usuario` INT NOT NULL,
  `tbl_empresa_idtbl_empresa` INT NOT NULL,
  `valor_final` INT NULL,
  PRIMARY KEY (`num_factura`),
  INDEX `fk_tbl_factura_tbl_cliente1_idx` (`tbl_cliente_idtbl_cliente` ASC) VISIBLE,
  INDEX `fk_tbl_factura_tbl_usuario1_idx` (`tbl_usuario_idtbl_usuario` ASC) VISIBLE,
  INDEX `fk_tbl_factura_tbl_empresa1_idx` (`tbl_empresa_idtbl_empresa` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_factura_tbl_cliente1`
    FOREIGN KEY (`tbl_cliente_idtbl_cliente`)
    REFERENCES `estructuras-database`.`tbl_cliente` (`idtbl_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_factura_tbl_usuario1`
    FOREIGN KEY (`tbl_usuario_idtbl_usuario`)
    REFERENCES `estructuras-database`.`tbl_usuario` (`idtbl_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_factura_tbl_empresa1`
    FOREIGN KEY (`tbl_empresa_idtbl_empresa`)
    REFERENCES `estructuras-database`.`tbl_empresa` (`idtbl_empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_producto` (
  `idtbl_producto` INT NOT NULL,
  `nombre_producto` VARCHAR(45) NULL,
  `cantidad` VARCHAR(45) NULL,
  `precio_unitario` INT NULL,
  PRIMARY KEY (`idtbl_producto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estructuras-database`.`tbl_factura_has_tbl_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estructuras-database`.`tbl_factura_has_tbl_producto` (
  `tbl_factura_num_factura` INT NOT NULL,
  `tbl_producto_idtbl_producto` INT NOT NULL,
  PRIMARY KEY (`tbl_factura_num_factura`, `tbl_producto_idtbl_producto`),
  INDEX `fk_tbl_factura_has_tbl_producto_tbl_producto1_idx` (`tbl_producto_idtbl_producto` ASC) VISIBLE,
  INDEX `fk_tbl_factura_has_tbl_producto_tbl_factura_idx` (`tbl_factura_num_factura` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_factura_has_tbl_producto_tbl_factura`
    FOREIGN KEY (`tbl_factura_num_factura`)
    REFERENCES `estructuras-database`.`tbl_factura` (`num_factura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_factura_has_tbl_producto_tbl_producto1`
    FOREIGN KEY (`tbl_producto_idtbl_producto`)
    REFERENCES `estructuras-database`.`tbl_producto` (`idtbl_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
