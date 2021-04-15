package com.boumaiza.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
		String sql = "INSERT INTO car (matricule, owner, type, dateE) VALUES (?, ?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, car.getMatricule());
		statement.setString(2, car.getOwner());
		statement.setString(3, car.getType());
		statement.setString(4, car.getdateE());

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
		    String dateE = resultSet.getString("dateE");
		    
			Car car= new Car(id, matricule, owner, type, dateE);
			car.setprice(getPrice(car));
			System.out.println(car.getPrice());
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
			
			
		    LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		    String formattedDate = myDateObj.format(myFormatObj);

		    String dateE = formattedDate;
			car = new Car(id, matricule, owner, type, dateE);
		}
		
		resultSet.close();
		statement.close();
		
		return car;
	}
	
	public long getPrice(Car c) {
		
	    LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    String datenow = myDateObj.format(myFormatObj);
	    
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        long difference_In_Seconds = 0;
        long difference_In_Time = 0;
        long difference_In_Minutes = 0;
        long difference_In_Hours = 0;
        try {
			java.util.Date d1 = sdf.parse(c.getdateE());
			java.util.Date d2 = sdf.parse(datenow);
			
            difference_In_Time = d2.getTime() - d1.getTime();

            difference_In_Hours= (difference_In_Time/ (1000 * 60 * 60)) % 24;

            
            difference_In_Seconds= (difference_In_Time / 1000) % 60;
            
            difference_In_Minutes= difference_In_Hours* 60 + (difference_In_Time/ (1000 * 60)) % 60;

            System.out.println("total : " + difference_In_Minutes);
            
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return difference_In_Minutes;
	}
}