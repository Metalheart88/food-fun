<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
                <c:if test="${isEdit}">
                    Edit Profile
                </c:if>
                <c:if test="${isNew}">
                    Registration Form
                </c:if>
         </title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="container">

            <div class="title-container">
                <h1 class="main-header">
                    <c:if test="${isEdit}">
                        EDIT PROFILE
                    </c:if>
                    <c:if test="${isNew}">
                        REGISTRATION FORM
                    </c:if>
                </h1>
            </div>

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

             <c:if test="${isEdit}">
                <form action="${pageContext.request.contextPath}/user/update" method="post" role="form">
            </c:if>
            <c:if test="${isNew}">
                <form action="${pageContext.request.contextPath}/user/insert" method="post" role="form">
            </c:if>

            <c:if test="${isEdit}">
                <input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
            </c:if>
        <div style="display: flex; justify-content: center;">
            <div class="login-form" style="padding: 0 16px;">
                <div class="form-group">
                    <label for="email">
                        Email: <input class="form-control" type="text" id="email" name="email" value="<c:out value='${customer.email}' />" />
                    </label>
                </div>
            <c:if test="${isNew}">
                <div class="form-group">
                    <label for="password">
                        Password: <input class="form-control" type="password" id="password" name="password" value="<c:out value='${customer.password}' />" />
                    </label>
                </div>
            </c:if>
                <div class="form-group">
                    <label for="name">
                        Name: <input class="form-control" type="text" id="name" name="name" value="<c:out value='${customer.name}' />" />
                    </label>
                </div>
                 <div class="form-group" style="text-align: left; margin-bottom: 9%;">
                    <label for="country" style="width: 19%; margin-bottom: 0;">Country:</label>
                        <select name='country' class="form-control" style="font-weight: 700;">
                                <option value="${customer.country}" selected>${customer.country}</option>
                                <c:forEach items="${countries}" var="country">
                                   <c:if test="${country != selected && customer.country != country}">
                                        <option value="${country}">${country}</option>
                                  </c:if>
                                </c:forEach>
                            </select>

                </div>
                <div class="form-group">
                    <label for="province">
                        Province: <input class="form-control" type="text" id="province" name="province" value="<c:out value='${customer.province}' />" />
                    </label>
                </div>
            </div>
            <div class="login-form" style="padding: 0 16px;">
                <div class="form-group">
                    <label for="street_name">
                        Street Name: <input class="form-control" type="text" id="street_name" name="street_name" value="<c:out value='${customer.streetName}' />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="street_number">
                        Street number: <input style="width: 210px;" class="form-control" type="number" id="street_number" name="street_number" value="<c:out value='${customer.streetNumber}' />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="postal_code">
                        Postal Code: <input class="form-control" type="text" id="postal_code" name="postal_code" value="<c:out value='${customer.postalCode}' />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="phone">
                        Phone: <input class="form-control" type="text" id="phone" name="phone" value="<c:out value='${customer.phone}' />" />
                    </label>
                </div>
                  </div>

                </div>
                <div style="text-align: center; padding-top: 20px;">
                 <c:if test="${isNew}">
                    <input type="submit" value="REGISTER" class="btn signup" style="width: 19%; font-weight: 700; "/>
                </c:if>
                <c:if test="${isEdit}">
                    <input type="submit" value="SAVE" class="btn btn-success" style="width: 19%; font-weight: 700"/>
                </c:if>
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>