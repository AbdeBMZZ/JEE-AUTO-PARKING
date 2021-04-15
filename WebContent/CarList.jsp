<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Parking</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<style>
		#myTable{
			width:900px;
			margin-top: 30px;
		}
		#aabb{
		    margin: 30px;
		}
	</style>
</head>
<body>

	<center>
		<div class="alert alert-success" role="alert">
			<h1 id="titleID">Automatic parking</h1>
		</div>
        <h2>
        	<a href="new"><button type="button" class="btn btn-secondary">Add New Car</button></a>
        	<a href="list"><button type="button" class="btn btn-secondary">All Cars</button></a>
        	
        </h2>
	</center>
    <div align="center">
	<caption><h4>cars in the parking lot</h4></caption>
	
	<center id="aabb">
		<div class="input-group rounded" style ="width:300px;">
		  <input id="myInput" type="search" class="form-control rounded" onkeyup="myFunction()" placeholder="Search" aria-label="Search"
		    aria-describedby="search-addon" />
		  <span class="input-group-text border-0" id="search-addon">
		    <i class="fas fa-search"></i>
		  </span>
		</div>
	</center>
	

        <table id="myTable" class="table table-bordered ">
		  <thead>
		    <tr>
		      <th scope="col">matricule</th>
		      <th scope="col">Owner</th>
		      <th scope="col">Type</th>
		      <th scope="col">Date</th>
			  <th scope="col">Price</th>
			  <th scope="col">Actions</th>

		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <c:forEach var="car" items="${listCar}">
                <tr>
                    <td><c:out value="${car.matricule}" /></td>
                    <td><c:out value="${car.owner}" /></td>
                    <td><c:out value="${car.type}" /></td>
                    <td><c:out value="${car.dateE}" /></td>
                    <td><c:out value="${car.price}" /> DH</td>

                    <td>
                    	<a href="edit?id=<c:out value='${car.id}' />"><button type="button" class="btn btn-primary">Edit</button></a>
                    	
                    	<a href="delete?id=<c:out value='${car.id}' />"><button  type="button" class="btn btn-danger">CheckOut</button></a>                    	

                    </td>
                </tr>
            </c:forEach>
		    </tr>
		  </tbody>
		</table>
    </div>
    
	<script>
	
		function myFunction() {
		  var input, filter, table, tr, td, i, txtValue;
		  
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[2	];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		}
	</script>
</body>
</html>
