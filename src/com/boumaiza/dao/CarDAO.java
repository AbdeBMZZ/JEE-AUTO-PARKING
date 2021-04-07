package com.boumaiza.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.boumaiza.models.Car;

public class CarDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public CarDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertCar(Car car) throws SQLException {
		String sql = "INSERT INTO car (matricule, owner, type) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, car.getMatricule());
		statement.setString(2, car.getOwner());
		statement.setString(3, car.getType());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Car> listAllCars() throws SQLException {
		List<Car> listcar = new ArrayList<>();
		
		String sql = "SELECT * FROM car";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("car_id");
			String matricule = resultSet.getString("matricule");
			String owner = resultSet.getString("owner");
			String type = resultSet.getString("type");
			
			Car car= new Car(id, matricule, owner, type);
			listcar.add(car);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listcar;
	}
	
	public boolean deleteCar(Car car) throws SQLException {
		String sql = "DELETE FROM car where car_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, car.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateCar(Car car) throws SQLException {
		String sql = "UPDATE car SET matricule = ?, owner = ?, type = ?";
		sql += " WHERE car_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, car.getMatricule());
		statement.setString(2, car.getOwner());
		statement.setString(3, car.getType());
		statement.setInt(4, car.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Car getCar(int id) throws SQLException {
		Car car= null;
		String sql = "SELECT * FROM car WHERE car_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String matricule = resultSet.getString("matricule");
			String owner = resultSet.getString("owner");
			String type = resultSet.getString("type");
			
			car = new Car(id, matricule, owner, type);
		}
		
		resultSet.close();
		statement.close();
		
		return car;
	}
}