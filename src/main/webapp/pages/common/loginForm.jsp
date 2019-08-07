<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
                Login
         </title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="container">
              <c:if test="${message != null}">
                    <div class="danger alert <c:out value='${message.type.reference}' />" style="width: 25%; margin: 20px 82%;">
                      <strong><c:out value='${message.text}' /></strong>
                    </div>
                    <center>
                        <div>
                            <span></span>
                        <div>
                    </center>
                </c:if>
            <div class="title-container">
                <h1 class="main-header">
                    LOGIN
                </h1>
            </div>
            <form action="${pageContext.request.contextPath}/login" method="post" role="form">
                <div class="login-form">
                    <div class="form-group">
                        <label for="email">
                            Email: <input class="form-control" type="text" id="email" name="email" />
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="password">
                            Password: <input class="form-control" type="password" id="password" name="password" />
                        </label>
                    </div>
                    <input type="submit" value="LOGIN" class="btn signup" style="width: 19%; font-weight: 700;"/>
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>