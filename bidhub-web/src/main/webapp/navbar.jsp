<nav class="navbar">
    <div class="nav-container">
        <div class="nav-logo">
            <a href="index.jsp">BidHub</a>
        </div>
        <ul class="nav-menu">
            <li class="nav-item">
                <a href="index.jsp" class="nav-link">Home</a>
            </li>
            <li class="nav-item">
                <a href="all-bids" class="nav-link">All Bids</a>
            </li>

            <%if(session.getAttribute("user") != null){%>
            <li class="nav-item">
                <a href="signOut" class="sign-out-btn">Sign Out</a>
            </li>
            <%
            }else{%>
            <li class="nav-item">
                <a href="register.jsp" class="register-btn">Register</a>
            </li>
            <%
            }%>
        </ul>
    </div>
</nav>