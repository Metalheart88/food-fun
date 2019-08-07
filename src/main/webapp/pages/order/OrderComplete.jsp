<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html style="background-color: white; background-image: none;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Complete</title>
         <link rel="stylesheet" href="https://unpkg.com/@coreui/coreui/dist/css/coreui.min.css">
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body style="background-color: white; background-image: none;">
        <%@ include file = "/pages/common/menu.jsp" %>
        <c:if test="${message != null}">
            <div class="alert-container">
                <div style="padding: 15px 35px;" class="alert <c:out value='${message.type.reference}' />">
                  <strong><c:out value='${message.text}' /></strong>
                </div>
            </div>
        </c:if>
        <div class="container">

            <div class="title-container">
                <h1 class="main-header" style="color: #333;">
                    Thank You!
                    <br/>
                    Your order is on it's way, <span style="color: crimson; -webkit-text-stroke: 1px darkred;"> ${customer.name} </span>!
                </h1>
                <div class="order-complete-div">Order number is <span class="order-complete-span"> ${orderId}. </span></div>
                <div class="order-complete-div">Your order will be delivered <span class="order-complete-span"> ${deliveryDate}. </span></div>
                <div class="order-complete-div">Our courier will call you at <span class="order-complete-span"> ${customer.phone} </span> upon delivery.</div>
                <img style="height: 50%;" src="/static/images/delivery_man.jpg" />
            </div>
        </div>


        <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/@coreui/coreui/dist/js/coreui.min.js"></script>
    </body>
</html>