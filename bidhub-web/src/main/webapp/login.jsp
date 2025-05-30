<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - BidHub</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Include Navigation -->
    <jsp:include page="navbar.jsp" />
    
    <div class="container">
        <div class="form-container">
            <h1>Login to BidHub</h1>
            <form class="auth-form" action="login" method="post">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit" class="submit-button">Login</button>
                
                <p class="form-footer">
                    Don't have an account? <a href="register.jsp">Register here</a>
                </p>

                <% String error = (String) request.getAttribute("error");
                    if (error != null) { %>
                <p style="color:red;"><%= error %></p>
                <% } %>
            </form>
        </div>
    </div>
    
    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>