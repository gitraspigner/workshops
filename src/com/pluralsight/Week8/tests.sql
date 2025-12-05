 # Ravi Spigner
# 11/19/2025
# Workbook 7-Workshop 7: Car Dealership - Test Database
use dealership;
# 1. Get all dealerships
SELECT * FROM Dealerships;
# 2. Find all vehicles for a specific dealership
SELECT v.* FROM Dealerships d JOIN Inventory i ON d.dealership_id = i.dealership_id
JOIN Vehicles v ON i.vin = v.vin
WHERE d.name = 'City Motors'; # Vehicles (vin 1001, 1002 SOLD but NOT DELETED FROM INVENTORY)
# 3. Find a car by VIN
SELECT * FROM Vehicles WHERE vin = 1003; # Not sold at all
# 4. Find the dealership where a certain car is located, by VIN
SELECT d.name, v.name, v.vin FROM Dealerships d JOIN Inventory i ON d.dealership_id = i.dealership_id JOIN Vehicles v ON v.vin = i.vin WHERE v.vin = 1003;
# 5. Find all Dealerships that have a certain car type (i.e. Red Ford Mustang, # I used Sedan Deluxe for this)
SELECT d.name, v.name FROM Dealerships d JOIN Inventory i ON d.dealership_id = i.dealership_id JOIN Vehicles v ON i.vin = v.vin WHERE v.name = 'Sedan Deluxe';
# 6. Get all sales information for a specific dealer for a specific date range
#Should display Bob & Jim Transactions (dates stored as YYYY-MM-DD) from City Motors:
SELECT s.* FROM SalesContracts s JOIN Dealerships d ON s.dealership_id = d.dealership_id
JOIN Inventory i ON  i.vin = s.vin
JOIN Vehicles v ON i.vin = v.vin WHERE d.name = 'City Motors' AND 
v.sold = 1 AND s.date BETWEEN '2025-01-01' AND '2025-11-01';
#Extra: Should only Display Jim Dale Transaction (dates stored as YYYY-MM-DD) from City Motors:
SELECT s.* FROM SalesContracts s JOIN Dealerships d ON s.dealership_id = d.dealership_id
JOIN Inventory i ON  i.vin = s.vin
JOIN Vehicles v ON i.vin = v.vin WHERE d.name = 'City Motors' AND 
v.sold = 1 AND s.date BETWEEN '2025-01-01' AND '2025-03-01';
#Extra: Should only Display Bob Smith Transaction (dates stored as YYYY-MM-DD) from City Motors:
SELECT s.* FROM SalesContracts s JOIN Dealerships d ON s.dealership_id = d.dealership_id
JOIN Inventory i ON  i.vin = s.vin
JOIN Vehicles v ON i.vin = v.vin WHERE d.name = 'City Motors' AND 
v.sold = 1 AND s.date BETWEEN '2025-03-01' AND '2025-11-01';

# Make Inventory reflect Sales Contracts created
DELETE FROM Inventory WHERE vin = 1002;
DELETE FROM Inventory WHERE vin = 1001;


#Extra: DO NOT display Alice Transaction OR any transactions at all (dates stored as YYYY-MM-DD) from Premium Cars:
SELECT s.* FROM SalesContracts s JOIN Dealerships d ON s.dealership_id = d.dealership_id
JOIN Inventory i ON  i.vin = s.vin
JOIN Vehicles v ON i.vin = v.vin WHERE d.name = 'Premium Cars' AND 
v.sold = 1 AND s.date BETWEEN '2025-01-01' AND '2025-12-31';
#Extra: DO NOT display transaction (dates stored as YYYY-MM-DD) from AutoHub:
SELECT s.* FROM SalesContracts s JOIN Dealerships d ON s.dealership_id = d.dealership_id
JOIN Inventory i ON  i.vin = s.vin
JOIN Vehicles v ON i.vin = v.vin WHERE d.name = 'AutoHub' AND 
v.sold = 0 AND s.date BETWEEN '2025-01-01' AND '2025-12-31';