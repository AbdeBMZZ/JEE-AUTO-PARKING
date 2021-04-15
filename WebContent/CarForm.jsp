<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Books Store Application</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
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
		<c:if test="${car != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${car == null}">
			<form action="insert" method="post">
        </c:if>
        	<caption><h4>Add New Car</h4></caption>
       
        <table  cellpadding="6">

        		<c:if test="${car != null}">
        			<input type="hidden" name="id" value="<c:out value='${car.id}' />" />
        		</c:if>            
            <tr>
                <th>Matricule: </th>
                <td>
                	<input type="text" name="matricule" size="45"
                			value="<c:out value='${car.matricule}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Owner: </th>
                <td>
                	<input id="txtV" type="text" name="owner" size="45"
                			value="<c:out value='${car.owner}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Type: </th>
                <td>
                	<select name="type" class="form-select" aria-label="Default select example">
					  <option selected><c:out value='${car.type}' /></option>
					  <option value="Car">Car</option>
					  <option value="Truck">Truck</option>
					  <option value="Bike">Bike</option>
					</select>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            	<button type="submit" class="btn btn-success">Add</button>            	
            	</td>
            </tr>
        </table>
        
        </form>
    </div>	
    

</body>
</html>
