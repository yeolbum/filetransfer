<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8">
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap/bootstrap.theme.min.css" />
	<script type="text/javascript" src="js/jquery/jquery-2.1.4.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Panel heading</div>
		<div class="panel-body">
			<p>...</p>
		</div>

		<!-- Table -->
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Username</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${files}" var="file">
					<tr>
						<th scope="row">1</th>
						<td>${file.path}</td>
						<td>Otto</td>
						<td>@mdo</td>
					
					</tr>
					
				</c:forEach>
				<tr>
					<th scope="row">1</th>
					<td>Mark</td>
					<td>Otto</td>
					<td>@mdo</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacob</td>
					<td>Thornton</td>
					<td>@fat</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Larry</td>
					<td>the Bird</td>
					<td>@twitter</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>

