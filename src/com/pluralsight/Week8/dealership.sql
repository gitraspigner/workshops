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
# ---------------------------------------------------------------------- #
# Add table "Sales Contracts"                                            #
# ---------------------------------------------------------------------- #
CREATE TABLE `SalesContracts` (
    `date` DATE NOT NULL,
    `vin` INTEGER NOT NULL,
    `dealership_id` INTEGER NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `price` INTEGER NOT NULL,
    FOREIGN KEY (`vin`) REFERENCES `Vehicles`(`vin`),
    FOREIGN KEY (`dealership_id`) REFERENCES `Dealerships`(`dealership_id`),
    PRIMARY KEY (`vin`)
);
# ---------------------------------------------------------------------- #
# TODO (If time allows): Add table "Lease Contracts"                     #
# ---------------------------------------------------------------------- #

# ---------------------------------------------------------------------- #
# Insert test data (Generated via ChatGPT)                               #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add info into "Dealerships"                                            #
# ---------------------------------------------------------------------- #
INSERT INTO Dealerships (name, address, phone) VALUES
('City Motors', '123 Main St', '555-1234'),
('AutoHub', '456 Oak Ave', '555-5678'),
('Premium Cars', '789 Pine Rd', '555-9012');
# ---------------------------------------------------------------------- #
# Add info into "Vehicles"                                               #
# ---------------------------------------------------------------------- #
INSERT INTO Vehicles (vin, sold, name, vehicle_type, year, make, model, color, odometer, price) VALUES
(1001, FALSE, 'Family SUV', 'SUV', 2020, 'Toyota', 'RAV4', 'Red', 15000, 25000),
(1002, FALSE, 'Sedan Deluxe', 'Sedan', 2019, 'Honda', 'Accord', 'Blue', 20000, 22000),
(1003, FALSE, 'Sportster', 'Coupe', 2021, 'Ford', 'Mustang', 'Black', 5000, 35000),
(1004, TRUE, 'Compact', 'Hatchback', 2018, 'Volkswagen', 'Golf', 'White', 30000, 18000);
# ---------------------------------------------------------------------- #
# Add info into "Inventory"                                              #
# ---------------------------------------------------------------------- #
INSERT INTO Inventory (dealership_id, vin) VALUES
(1, 1001),
(1, 1002),
(2, 1003),
(3, 1004);  # Sold vehicle, SHOULD NOT BE in inventory
DELETE FROM Inventory WHERE vin = 1004;
# ---------------------------------------------------------------------- #
# Add info into "Sales Contracts"                                        #
# ---------------------------------------------------------------------- #
INSERT INTO SalesContracts (date, vin, dealership_id, name, email, price) VALUES
('2025-10-01', 1004, 3, 'Alice Johnson', 'alice@example.com', 18000), # Already-sold vehicle
('2025-10-05', 1002, 1, 'Bob Smith', 'bob@example.com', 22000),
('2025-01-05', 1001, 1, 'Jim Dale', 'jim@example.com', 25000);

UPDATE Vehicles SET sold = 1 WHERE vin = 1004; # Already sold vehicle, should be 1
UPDATE Vehicles SET sold = 1 WHERE vin = 1002; # New transaction
UPDATE Vehicles SET sold = 1 WHERE vin = 1001; # New transaction
