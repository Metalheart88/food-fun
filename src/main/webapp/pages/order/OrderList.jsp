<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>My Orders</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="wrap">
            <h1 style="color: white; text-align: center; padding: 20px;">MY ORDERS</h1>
            <div class="container" style="display: flex; justify-content: space-between; flex-flow: row wrap;">
                <c:if test="${message != null}">
                    <div class="alert <c:out value='${message.type.reference}' />">
                      <strong><c:out value='${message.text}' /></strong>
                    </div>
                </c:if>

                <div class="row">
                    <c:forEach var="order" items="${listOrders}">
                        <div class="order-container">
                            <div class="order-info" style="text-align: center; margin: 0 auto;">
                                <div class="order-split" style="display: block">
                                    <div class="delivery-keys">${order.orderDate}</div>
                                    </br>
                                    <div><span class="delivery-keys">Order Id:</span> ${order.orderId}</div>
                                    <div><span class="delivery-keys">Order Price:</span> $${order.totalPrice}</div>
                                    </br>
                                    <p>
                                      <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapse${order.orderId}" aria-expanded="false" aria-controls="collapse">
                                        Order Details
                                      </button>
                                    </p>
                                    </br>
                                    <div class="collapse" id="collapse${order.orderId}">
                                      <div class="card card-body">
                                        <div><span class="delivery-keys">Delivery Date:</span> ${order.delivery.deliveryTime}</div>
                                        <div><span class="delivery-keys">Ordered Qty:</span> ${order.orderQty}</div>
                                        <div><span class="delivery-keys">Ordered Food:</span> ${order.food.name}</div>
                                        <div><span class="delivery-keys">Ingredients:</span> ${order.food.ingredient} (${order.food.ingredientQty} each)</div>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>