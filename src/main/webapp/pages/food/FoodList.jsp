<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Menu</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="wrap">
            <h1 style="color: white; text-align: center; padding: 20px;">MAKE YOUR CHOICE</h1>
            <div class="container" style="display: flex; justify-content: space-between; flex-flow: row wrap;">
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

                <div class="row">
                    <c:forEach var="food" items="${listFood}" varStatus="loop">
                        <div class="col-sm-4" style="margin-bottom: 40px;">
                            <div class="card"  style="text-align: justify;">
                                <img style="border-radius: 5px; width: 360px; height: 280px; " src=${food.image} class="card-img-top" alt="food picture">
                                <div class="card-body" style="background-color: white; padding: 0px 20px; border-radius: 5px;">
                                    <h5 class="card-title" style="margin-bottom: 0; color: crimson; padding: 20px 0px; font-weight: 700; font-size: 20px;"><c:out value="${food.name}" /></h5>
                                    <p class="card-text" style="padding-bottom: 16px;"><c:out value="${food.recipe}" /></p>
                                </div>
                                <ul class="list-group list-group-flush" style="border-radius: 5px;">
                                  <li class="list-group-item"><strong>Price:</strong> $<c:out value="${food.price}" /></li>
                                  <li class="list-group-item"><strong>Ingredients:</strong> <c:out value="${food.ingredient}" /></li>
                                  <li class="list-group-item"><strong>Quantity:</strong> <c:out value="${food.ingredientQty}" /> each</li>
                                </ul>
                                <c:if test="${loggedUser != null}">
                                    <c:if test="${isAdmin}">
                                        <div class="card-body" style="display: flex; justify-content: space-between;">
                                           <a class="btn btn-success" href="${pageContext.request.contextPath}/orders/new?id=<c:out value='${food.id}' />" style="width: 110px;">Order</a>
                                    </c:if>
                                    <c:if test="${!isAdmin}">
                                        <div class="card-body" style="display: block;">
                                            <a class="btn btn-success" href="${pageContext.request.contextPath}/orders/new?id=<c:out value='${food.id}' />" style="width: 100%;">Order</a>
                                    </c:if>
                                       &nbsp;&nbsp;&nbsp;&nbsp;
                                       <c:if test="${isAdmin}">
                                           <a class="btn btn-info" href="${pageContext.request.contextPath}/food/edit?id=<c:out value='${food.id}' />" style="width: 110px;">Edit</a>
                                           &nbsp;&nbsp;&nbsp;&nbsp;
                                           <a class="btn signup" href="${pageContext.request.contextPath}/food/delete?id=<c:out value='${food.id}' />" style="width: 110px;">Delete</a>
                                       </c:if>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                     </c:forEach>
                </div>
            </div>
            <c:if test="${isAdmin}">
                <a style="display: flex; justify-content: center; width: 20%; margin: 40px auto;" href="${pageContext.request.contextPath}/food/new" role="button" class="btn btn-success btn-lg">Add New Recipe</a>
            </c:if>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>