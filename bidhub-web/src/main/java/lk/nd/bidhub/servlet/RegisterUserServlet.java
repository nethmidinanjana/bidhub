package lk.nd.bidhub.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.nd.bidhub.beans.UserManagerBean;
import lk.nd.bidhub.model.User;

import java.io.IOException;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    @EJB
    private UserManagerBean userManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(name, email, password);

        boolean success = userManager.registerUser(user);

        if(success){
            resp.sendRedirect("login.jsp");
        }else{
            req.setAttribute("error", "Email already in exists!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
