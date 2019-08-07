<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Profile</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="wrap">
            <section>
                <div class="container">
                    <c:if test="${message != null}">
                        <div class="alert <c:out value='${message.type.reference}' />">
                          <strong><c:out value='${message.text}' /></strong>
                        </div>
                        <center>
                            <div>
                                <span></span>
                            <div>
                        </center>
                    </c:if>
                    <table class="table table-hover">
                        <c:set var = "name" value = "${loggedUser.name}" />
                        <c:set var = "customer" value = "${fn:toUpperCase(name)}" />
                        <caption><h1 class="main-header">${customer}'S PROFILE</h1></caption>
                        <tr class="profile-table">
                            <th style="text-align: center">Name</th>
                            <th style="text-align: center">Email</th>
                            <th style="text-align: center">Phone</th>
                            <th style="text-align: center; width: 350px">Address</th>
                        </tr>
                        <tr class="profile-table">
                            <td style="text-align: center"><c:out value="${listUser.name}" /></td>
                            <td style="text-align: center"><c:out value="${listUser.email}" /></td>
                            <td style="text-align: center"><c:out value="${listUser.phone}" /></td>
                            <td style="text-align: center; width: 350px"><c:out value="${listUser.streetNumber} ${listUser.streetName}, ${listUser.country}, ${listUser.province} ${listUser.postalCode}" /></td>
                        </tr>
                    </table>
                    <div style="text-align: center; padding-top: 40px;">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/edit?id=<c:out value='${listUser.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/user/delete?id=<c:out value='${listUser.id}' />">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <%-- <a class="btn btn-warning" href="${pageContext.request.contextPath}/user/changePassword?id=<c:out value='${listUser.id}' />">Change Password</a> --%>
                    </div>


                    <%-- <a href="${pageContext.request.contextPath}/user/new" role="button" class="btn btn-info btn-lg">Add User</a> --%>
                </div>
            </section>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>