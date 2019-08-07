<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Food Fun</title>

        <link rel="stylesheet" href="https://unpkg.com/@coreui/coreui/dist/css/coreui.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "pages/common/menu.jsp" %>

        <div class="hero-image">
          <div class="hero-text">
              <h1 style="margin-top: 120px;">WELCOME TO FOOD FUN</h1>
              <br />
              <div style="width: 700px;" id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                  <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                </ol>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img style="height: 450px; margin-bottom: 40px; border-radius: 5px;" src="https://www.thespruceeats.com/thmb/oNcVv91T-xm3x0EsEL8ax7V_O14=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/Russiandumplings-pelmeni-GettyImages-673452064-596ed4f5845b340011a7b799.jpg" class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img style="height: 450px; margin-bottom: 40px; border-radius: 5px;" src="https://crainny.files.wordpress.com/2014/10/img_4345.jpg?w=630&h=418
                                " class="d-block w-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img style="height: 450px; margin-bottom: 40px; border-radius: 5px;" src="https://st2.depositphotos.com/3278179/6947/i/950/depositphotos_69479483-stock-photo-dish-of-pilaf-national-uzbek.jpg
                               " class="d-block w-100" alt="...">
                  </div>
                   <div class="carousel-item">
                      <img style="height: 450px; margin-bottom: 40px; border-radius: 5px;" src="https://media.eggs.ca/assets/RecipeThumbs/_resampled/FillWyIxMjgwIiwiNzIwIl0/Basic-Fried-Eggs-CMS.jpg
                                 " class="d-block w-100" alt="...">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>
          </div>
        </div>
        <h1>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <script>
            $('.carousel').carousel({
              interval: 2000
            });
        </script>
    </body>
</html>