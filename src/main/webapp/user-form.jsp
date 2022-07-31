<%--
  Created by IntelliJ IDEA.
  User: ozodbek
  Date: 10.06.2022
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand"> User Management Application </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="/list"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="/update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="/insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>

                        <fieldset class="form-group">
                            <label>User Login</label> <input type="text"
                                                            value="<c:out value='${user.login}' />" class="form-control"
                                                            name="login" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Password</label> <input type="text"
                                                            value="<c:out value='${user.password}' />" class="form-control"
                                                            name="password" required="required">
                        </fieldset>



                        <fieldset class="form-group">
                            <label>User EducationId</label> <input type="text"
                                                            value="<c:out value='${user.educationId}' />" class="form-control"
                                                            name="educationId" required="required">
                        </fieldset>


                    <fieldset class="form-group">
                        <label>User Name</label> <input type="text"
                                                        value="<c:out value='${user.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Country</label> <input type="text"
                                                         value="<c:out value='${user.country}' />" class="form-control"
                                                         name="country">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Language </label> <input type="text"
                                                           value="<c:out value='${user.language}' />" class="form-control"
                                                           name="language">
                    </fieldset>

                        <fieldset class="form-group">
                            <label>User Birthday </label> <input type="text"
                                                                 value="<c:out value='${user.birthday}' />" class="form-control"
                                                                 name="birthday">
                        </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>