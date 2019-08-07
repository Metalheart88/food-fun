<div id="sse2">
  <div id="sses2">
    <ul>
          <a id="logo" href="/" style="background-color: transparent; padding: 0;">
            <img src="https://img.icons8.com/bubbles/2x/food.png" width="65px" height="65px" />
          </a>
      <li><a href="${pageContext.request.contextPath}/food/list">Menu</a></li>
      <li><a href="${pageContext.request.contextPath}/">Pricing</a></li>
      <li><a href="${pageContext.request.contextPath}/">How it works</a></li>
      <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </ul>
    <ul>
        <c:if test="${loggedUser == null}">
            <li><a href="${pageContext.request.contextPath}/login" class="menu-button">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/user/signup" class="signup">Sign Up</a></li>
        </c:if>
        <c:if test="${loggedUser != null}">
            <li><a href="${pageContext.request.contextPath}/orders?id=<c:out value='${loggedUser.id}' />">My Orders</a></li>
            <li><a href="${pageContext.request.contextPath}/user/?id=<c:out value='${loggedUser.id}' />">My Account</a></li>
            <li><a href="${pageContext.request.contextPath}/logout" class="signup">Logout</a></li>
        </c:if>
    </ul>
  </div>
</div>