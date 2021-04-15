package com.boumaiza.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boumaiza.dao.CarDAO;
import com.boumaiza.models.Car;

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CarDAO carDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		carDAO = new CarDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCar(request, response);
				break;
			case "/delete":
				deleteCar(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCar(request, response);
				break;
			default:
				listCar(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Car> listCar = carDAO.listAllCars();
		request.setAttribute("listCar", listCar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CarList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Car existingCar = carDAO.getCar(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
		request.setAttribute("car", existingCar);
		dispatcher.forward(request, response);

	}

	private void insertCar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String matricule = request.getParameter("matricule");
		String owner = request.getParameter("owner");
		String type = request.getParameter("type");
		
	    LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String dateE = formattedDate;
	    
		Car newCar = new Car(matricule, owner, type, dateE);
		carDAO.insertCar(newCar);
		response.sendRedirect("list");
	}

	private void updateCar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {		
		int id = Integer.parseInt(request.getParameter("id"));
		String matricule = request.getParameter("matricule");
		String owner = request.getParameter("owner");
		String type = request.getParameter("type");

	    LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String dateE = formattedDate;
	    
		Car car= new Car(id, matricule, owner, type, dateE);
		carDAO.updateCar(car);
		response.sendRedirect("list");
	}

	private void deleteCar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Car car = new Car(id);
		carDAO.deleteCar(car);
		response.sendRedirect("list");

	}

}
