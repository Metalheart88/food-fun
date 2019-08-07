<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Order</title>
         <link rel="stylesheet" href="https://unpkg.com/@coreui/coreui/dist/css/coreui.min.css">
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="container">
            <div class="title-container">
                <h1 class="main-header">
                    New Order
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
            <div class="order-container">
                <div class="order-info">
                    <form action="${pageContext.request.contextPath}/orders/insert" method="post" role="form">

                        <input type="hidden" name="foodId" value="<c:out value='${food.id}' />" />
                        <input type="hidden" name="foodName" value="<c:out value="${food.name}" />" />
                        <input type="hidden" name="foodRecipe" value="<c:out value="${food.recipe}" />" />
                        <input type="hidden" name="price" id="price" value="<c:out value='${food.price}' />" />
                        <input type="hidden" name="ingredients" value="<c:out value='${food.ingredient}' />" />
                        <input type="hidden" name="totalPrice" id="tPrice" value=1 />
                        <input type="hidden" name="customerId" id="customer" value="<c:out value='${loggedUser.id}' />" />

                        <div class="order-split">
                            <img src=${food.image} alt=${food.name} style="margin-right: 20px; width: 320px; height: 300px;"/>
                            <div>
                                <div style="font-weight: 700; color: crimson; margin-bottom: 20px; font-size: 25px;">${food.name}</div>
                                <div style="width: 345px; text-align: justify;">${food.recipe}</div>
                            </div>
                        </div>
                        <div style="display: flex;">
                            <div style="width: 50%;">
                                <div style="display: flex; align-items: center; margin: 20px 0;"><span style="font-weight: 700; padding-right: 5px;">Ingredients: </span> ${food.ingredient}</div>
                                <div style="display: flex; align-items: center; margin: 20px 0;"><span style="font-weight: 700; padding-right: 5px;">Price: </span> $${food.price}</div>
                                <div class="form-group" style="display: flex; align-items: center; margin: 20px 0;">
                                    <span style="font-weight: 700; padding-right: 5px;">Quantity: </span> <input class="form-control" id="order-qty" type="number" min="1" name="quantity" value=1 style="margin-left: 7px;" />
                                </div>
                                <div class="form-group" style="display: flex; align-items: center;">
                                    <span style="font-weight: 700; padding-right: 5px;">Total price: </span> $<span id="root" style="margin-right: 7px;">${food.price}</span> <a class="btn btn-info" id="qty">Update</a>
                                </div>
                            </div>
                            <div style="width: 50%; padding: 20px 0;">
                                <fieldset class="form-group">
                                  <label>Credit Card Number: </label>
                                  <div class="input-group" style="display: flex;">
                                    <span class="input-group-prepend">
                                      <span class="input-group-text">
                                        <i class="fa fa-credit-card"></i>
                                      </span>
                                    </span>
                                    <input type="text" style="width: 70%;" class="form-control" id="ccn" placeholder="0000 0000 0000 0000">
                                  </div>
                                  <small class="text-muted">ex. 9999 9999 9999 9999</small>
                                </fieldset>
                                <div class="form-group">
                                    <label>Expiry Date: </label>
                                    <input type="text" class="form-control" aria-describedby="expiryHelp" placeholder="MM-YYYY" max="6">
                                    <small id="expiryHelp" class="form-text text-muted">Please enter expiry date (numbers only)</small>
                                </div>
                            </div>
                        </div>
                        <div style="text-align: center;">
                            <input type="submit" value="Make Payment" class="btn btn-success" style="width: 30%;"/>
                        </div>
                    </form>
                </div>
                <div class="order-user-info">
                    <div class="delivery-header">DELIVERY INFO</div>
                    <div style="display: block;">
                        <div class="delivery-table" style="padding-top: 0;"><span class="delivery-keys">Name:</span> ${loggedUser.name}</div>
                        <div class="delivery-table"><span class="delivery-keys">Street Number:</span> ${loggedUser.streetNumber}</div>
                        <div class="delivery-table"><span class="delivery-keys">Street Name:</span> ${loggedUser.streetName}</div>
                        <div class="delivery-table"><span class="delivery-keys">City:</span> Winnipeg</div>
                        <div class="delivery-table"><span class="delivery-keys">Province:</span> ${loggedUser.province}</div>
                        <div class="delivery-table"><span class="delivery-keys">Country:</span> ${loggedUser.country}</div>
                        <div class="delivery-table"><span class="delivery-keys">Postal Code:</span> ${loggedUser.postalCode}</div>
                        <div class="delivery-table"><span class="delivery-keys">Email:</span> ${loggedUser.email}</div>
                        <div class="delivery-table"><span class="delivery-keys">Phone:</span> ${loggedUser.phone}</div>
                    </div>
                </div>
            </div>
        </div>


        <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/@coreui/coreui/dist/js/coreui.min.js"></script>

        <script>
            $("#qty").click(function () {
                var total = $("#order-qty").val()*$('#price').val();
                $('#root').html(total);
                $('#tPrice').val(total);
            });
        </script>
    </body>
</html>