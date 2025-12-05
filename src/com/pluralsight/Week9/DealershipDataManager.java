package com.pluralsight.Week9;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class DealershipDataManager {
    private DataSource dataSource;
    public DealershipDataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addSalesContract(DealershipSalesContract contract) throws SQLException {
        //Create new sales contract
        String insertSql = "INSERT INTO SalesContracts (date, vin, dealership_id, name, email, price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setString(1, contract.getDate());
            insertStmt.setInt(2, contract.getVin());
            insertStmt.setInt(3, contract.getDealership_id());
            insertStmt.setString(4, contract.getName());
            insertStmt.setString(5, contract.getEmail());
            insertStmt.setInt(6, contract.getPrice());
            insertStmt.executeUpdate();
        }
        //Update Vehicle as sold
        String updateSql = "UPDATE Vehicles SET sold = TRUE WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setInt(1, contract.getVin());
            updateStmt.executeUpdate();
        }
        //Remove vehicle from inventory
        String deleteSql = "DELETE FROM Vehicles WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, contract.getVin());
            deleteStmt.executeUpdate();
        }
    }
    public List<DealershipVehicle> getAllVehicles()
            throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        //Perform Query To retrieve Vehicles
        String query = "SELECT * FROM Vehicles";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            try (ResultSet results = statement.executeQuery();) {
                //Process Results
                while (results.next()) {
                    int vin = results.getInt("vin");
                    boolean sold = results.getBoolean("sold");
                    int year = results.getInt("year");
                    String vehicleType = results.getString("vehicle_type");
                    String make = results.getString("make");
                    String model = results.getString("model");
                    String color = results.getString("color");
                    int odometer = results.getInt("odometer");
                    int price = results.getInt("price");
                    vehicles.add(new DealershipVehicle(vin, sold, year, make, model,
                            vehicleType, color, odometer, price));
                }
            }
        }
        //Return Results
        return vehicles;
    }
    public void addVehicle(DealershipVehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicles " +
                "(vin, sold, name, vehicle_type, year, make, model, color, odometer, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getVin());
            stmt.setBoolean(2, vehicle.isSold());
            stmt.setString(3, vehicle.getMake() + " " + vehicle.getModel()); // optional: derive name if needed
            stmt.setString(4, vehicle.getVehicle_type());
            stmt.setInt(5, vehicle.getYear());
            stmt.setString(6, vehicle.getMake());
            stmt.setString(7, vehicle.getModel());
            stmt.setString(8, vehicle.getColor());
            stmt.setInt(9, vehicle.getOdometer());
            stmt.setDouble(10, vehicle.getPrice());
            stmt.executeUpdate();
        }
    }
    public boolean removeVehicle(int vin) throws SQLException {
        String sql = "DELETE FROM Vehicles WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vin);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    public List<DealershipVehicle> getVehiclesUnderPrice(double maxPrice) throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE price <= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, maxPrice);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new DealershipVehicle(
                        rs.getInt("vin"),
                        rs.getBoolean("sold"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }
    public List<DealershipVehicle> getVehiclesByMakeModel(String make, String model) throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE make = ? AND model = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new DealershipVehicle(
                        rs.getInt("vin"),
                        rs.getBoolean("sold"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }
    public List<DealershipVehicle> getVehiclesByYear(int year) throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE year = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new DealershipVehicle(
                        rs.getInt("vin"),
                        rs.getBoolean("sold"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }
    public List<DealershipVehicle> getVehiclesByColor(String color) throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE color = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, color);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new DealershipVehicle(
                        rs.getInt("vin"),
                        rs.getBoolean("sold"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }
    public List<DealershipVehicle> getVehiclesUnderOdometer(int maxOdometer) throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE odometer <= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maxOdometer);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new DealershipVehicle(
                        rs.getInt("vin"),
                        rs.getBoolean("sold"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }
    public List<DealershipVehicle> getVehiclesByType(String type) throws SQLException {
        List<DealershipVehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles WHERE vehicle_type = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new DealershipVehicle(
                        rs.getInt("vin"),
                        rs.getBoolean("sold"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }
}
