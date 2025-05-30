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

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

    @EJB
    private UserManagerBean userManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userManager.authenticate(email, password);

        if(user != null){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
        }else{
            req.setAttribute("error", "Invalid login details!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
