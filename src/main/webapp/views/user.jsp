<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body class="container col-8" style="background-color: gray;">
	<div class="p-5 m-3"
		style="border: 3px solid black; background-color: white; border-radius: 20px">
		<h2>Use Management - CRUD</h2>
		<h4>Fill in the data below.</h4>
		<form class="form" action="/admin/save" method="post">
			<input type="text" name="id" value="${user.id}" placeholder="Id"
				class="form-control mb-2" required> <input type="password"
				name="password" value="${user.password}" placeholder="Password"
				class="form-control mb-2" required> <input type="text"
				name="fullname" value="${user.fullname}" placeholder="Full name"
				class="form-control mb-2" required> <input type="email"
				name="email" value="${user.email}" placeholder="Email"
				class="form-control mb-2" required> <label>Role:</label> <br>
			<input type="radio" name="admin" ${user.admin ? 'checked' : '' }
				value="true"> Admin <input type="radio" name="admin"
				${user.admin ? '' : 'checked' } value="false"> User <span
				class="ms-5" style="color: green;"><b><i>${message}</i></b></span>
			<div class="mt-2">
				<button class="btn btn-success">Save</button>
				<a class="btn btn-secondary" href="/">Reset</a>
				<a class="btn btn-outline-danger" href="/logout">Logout</a>
			</div>
		</form>
		<form action="/admin/search" class="form col-6 text-center mt-2 mx-auto"
			method="post">
			<input type="text" class="form-control w-75" value="${param.keyword}"
				name="keyword" onchange="this.form.submit()" placeholder="Tìm kiếm">
		</form>
		<table class="table bordered">
			<thead>
				<tr>
					<th>Id</th>
					<th>Password</th>
					<th>Full-name</th>
					<th>Email</th>
					<th>Role</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${users}">
					<tr>
						<td>${u.id}</td>
						<td>${u.password}</td>
						<td>${u.fullname}</td>
						<td>${u.email}</td>
						<td>${u.admin ? 'Admin' : 'User'}</td>
						<td><a class="btn btn-warning" href="/admin/edit?id=${u.id}">Edit</a>
							<a class="btn btn-danger" href="/admin/remove?id=${u.id}">Remove</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>