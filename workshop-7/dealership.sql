# Ravi Spigner
# 11/19/2025
# Workbook 7-Workshop 7: Car Dealership - Create Database
# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          Car Dealership                                  #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS dealership;
CREATE DATABASE IF NOT EXISTS dealership;
USE dealership;
# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add table "Dealerships"                                                #
# ---------------------------------------------------------------------- #
CREATE TABLE `Dealerships` (
	`dealership_id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(12) NOT NULL,
    PRIMARY KEY (`dealership_id`)
);
# ---------------------------------------------------------------------- #
# Add table "Vehicles"                                                   #
# ---------------------------------------------------------------------- #
CREATE TABLE `Vehicles` (
	`vin` INTEGER NOT NULL,
    `sold` BOOLEAN NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `vehicle_type` VARCHAR(50) NOT NULL,
    `year` INTEGER NOT NULL,
    `make` VARCHAR(50) NOT NULL,
    `model` VARCHAR(50) NOT NULL,
    `color` VARCHAR(50) NOT NULL,
    `odometer` INTEGER NOT NULL,
    `year` INTEGER NOT NULL,
    `price` INTEGER NOT NULL,
    PRIMARY KEY (`vin`)
);
# ---------------------------------------------------------------------- #
# Add table "Inventory"                                                  #
# ---------------------------------------------------------------------- #
CREATE TABLE `Inventory` (
	`dealership_id` INTEGER NOT NULL,
    `vin` INTEGER NOT NULL,
    FOREIGN KEY (`dealership_id`) REFERENCES `Dealerships`(`dealership_id`),
    FOREIGN KEY (`vin`) REFERENCES `Vehicles`(`vin`),
    PRIMARY KEY (`dealership_id`, `vin`)
);




